package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.CheckLogDao;
import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.utils.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
@Repository
@Qualifier("sqliteData")
public class SqliteCheckLogDao implements CheckLogDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long insertCheckLog(CheckLog checkLog) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO CHECK_LOG (date) VALUES (?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Formatter.formatDatabaseDateTime(checkLog.getDate()));
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public CheckLog getCheckLogById(long id) {
        String sql = "SELECT * " +
                "FROM CHECK_LOG " +
                "WHERE CHECK_LOG.id = ?";
        return (CheckLog) jdbcTemplate.queryForObject(sql, new CheckLogRowMapper(), id);
    }

    @Override
    public List<CheckLog> getAllCheckLogs() {
        String sql = "SELECT * " +
                "FROM CHECK_LOG " +
                "ORDER BY date desc";
        return (List<CheckLog>) jdbcTemplate.query(sql, new CheckLogRowMapper());
    }

    public class CheckLogRowMapper implements RowMapper {

        @Override
        public CheckLog mapRow(ResultSet resultSet, int i) throws SQLException {
            return new CheckLog(
                    resultSet.getLong("id"),
                    Formatter.convertDatabaseDateTime(resultSet.getString("date"))
            );
        }
    }
}
