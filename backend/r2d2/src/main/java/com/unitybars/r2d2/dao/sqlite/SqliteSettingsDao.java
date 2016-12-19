package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.SettingsDao;
import com.unitybars.r2d2.entity.MailSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;

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
}
