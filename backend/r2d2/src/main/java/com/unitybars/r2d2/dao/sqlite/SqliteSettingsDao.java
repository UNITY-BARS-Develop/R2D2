package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.SettingsDao;
import com.unitybars.r2d2.entity.CheckScheduleParameters;
import com.unitybars.r2d2.entity.CheckSenderParameters;
import com.unitybars.r2d2.entity.MailSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
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
                "FROM SETTINGS\n" +
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
}
