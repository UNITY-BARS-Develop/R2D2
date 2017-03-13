package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.SettingsDao;
import com.unitybars.r2d2.entity.CheckScheduleParameters;
import com.unitybars.r2d2.entity.CheckSenderParameters;
import com.unitybars.r2d2.entity.MailSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
@Repository
@Qualifier("sqliteData")
public class SqliteSettingsDao implements SettingsDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public MailSettings getMailSettings() {
        String sql = "SELECT * " +
                "FROM SETTINGS " +
                "WHERE SETTINGS.\"key\" like \"smtp%\"";
        return jdbcTemplate.query(sql, resultSet -> {
            HashMap<String, String> settings = new HashMap<>();
            while (resultSet.next()) {
                settings.put(resultSet.getString("key"), resultSet.getString("value"));
            }
            return new MailSettings(
                    settings.get("smtp.host"),
                    settings.get("smtp.username"),
                    settings.get("smtp.password"),
                    settings.get("smtp.port"),
                    settings.get("smtp.tlsenable").equalsIgnoreCase("true")
            );
        });
    }

    @Override
    public void updateMailSettings(MailSettings mailSettings) {
        List<String[]> parameters = new ArrayList<String[]>() {
            {
                add(new String[]{"smtp.host", mailSettings.getHost()});
                add(new String[]{"smtp.username", mailSettings.getUsername()});
                add(new String[]{"smtp.password", mailSettings.getPassword()});
                add(new String[]{"smtp.port", mailSettings.getPort()});
                add(new String[]{"smtp.tlsenable", String.valueOf(mailSettings.isStartTlsEnable())});
            }
        };
        updateSettings(parameters);
    }

    @Override
    public CheckSenderParameters getCheckSenderParameters() {
        String sql = "SELECT * " +
                "FROM SETTINGS " +
                "WHERE SETTINGS.\"key\" like \"sender%\"";
        return jdbcTemplate.query(sql, resultSet -> {
            Map<Object, ArrayList<String>> parameters = new HashMap<>();
            while (resultSet.next()) {
                String key = resultSet.getString("key");
                String value = resultSet.getString("value");
                ArrayList<String> parameter = parameters.computeIfAbsent(key, k -> new ArrayList<>());
                parameter.add(value);
            }
            CheckSenderParameters checkSenderParameters = new CheckSenderParameters();
            ArrayList<String> subjects = parameters.get("sender.mail.subject");
            checkSenderParameters.setMailSubject(subjects != null && subjects.size() > 0 ? subjects.get(0) : null);
            ArrayList<String> sendMessageIfSuccessList = parameters.get("sender.mail.send_if_success");
            if (sendMessageIfSuccessList != null && sendMessageIfSuccessList.size() > 0) {
                String sendMessageIfSuccess = sendMessageIfSuccessList.get(0);
                checkSenderParameters.setSendMessagesWhenSuccess(sendMessageIfSuccess != null
                        && sendMessageIfSuccess.equalsIgnoreCase("true"));
            } else {
                checkSenderParameters.setSendMessagesWhenSuccess(false);
            }
            return checkSenderParameters;
        });
    }

    @Override
    public CheckScheduleParameters getCheckScheduleParameters() {
        String sql = "SELECT * " +
                "FROM SETTINGS " +
                "WHERE SETTINGS.\"key\" like \"schedule%\"";
        return jdbcTemplate.query(sql, resultSet -> {
            HashMap<String, String> settings = new HashMap<>();
            while (resultSet.next()) {
                settings.put(resultSet.getString("key"), resultSet.getString("value"));
            }
            String periodStr = settings.get("schedule.period");
            int period;
            try {
                period = Integer.parseInt(periodStr);
            } catch (Exception e) {
                period = 0;
            }
            String isEnabledStr = settings.get("schedule.enable");
            return new CheckScheduleParameters(
                    period,
                    (isEnabledStr != null && isEnabledStr.equalsIgnoreCase("true"))
            );
        });
    }

    @Override
    public void updateScheduleParameters(CheckScheduleParameters checkScheduleParameters) {
        List<String[]> parameters = new ArrayList<String[]>() {
            {
                add(new String[]{"schedule.period", String.valueOf(checkScheduleParameters.getSchedulePeriod())});
                add(new String[]{"schedule.enable", String.valueOf(checkScheduleParameters.isEnableScheduler())});
            }
        };
        updateSettings(parameters);
    }

    @Override
    public void updateCheckSenderParameters(CheckSenderParameters checkSenderParameters) {
        List<String[]> parameters = new ArrayList<String[]>() {
            {
                add(new String[]{"sender.mail.subject", String.valueOf(checkSenderParameters.getMailSubject())});
                add(new String[]{"sender.mail.send_if_success", String.valueOf(checkSenderParameters.isSendMessagesWhenSuccess())});
            }
        };
        updateSettings(parameters);
    }

    private void updateSettings(List<String[]> parameters) {
        String sql = "UPDATE SETTINGS " +
                "SET value = ? " +
                "WHERE key = ?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                String[] property = parameters.get(i);
                preparedStatement.setString(1, property[1]);
                preparedStatement.setString(2, property[0]);
            }

            @Override
            public int getBatchSize() {
                return parameters.size();
            }
        });
    }

}
