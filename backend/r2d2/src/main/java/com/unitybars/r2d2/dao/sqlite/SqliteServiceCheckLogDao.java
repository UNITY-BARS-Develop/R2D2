package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.ServiceCheckLogDao;
import com.unitybars.r2d2.entity.ServiceCheckLog;
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
public class SqliteServiceCheckLogDao implements ServiceCheckLogDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long insertServiceCheckLog(ServiceCheckLog serviceCheckLog) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO SERVICE_CHECK_LOG (check_log_id, date, service_name)  VALUES  (?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, serviceCheckLog.getCheckLogId());
            ps.setString(2, Formatter.formatDatabaseDateTime(serviceCheckLog.getDate()));
            ps.setString(3, serviceCheckLog.getServiceName());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public ServiceCheckLog getServiceCheckLogById(long id) {
        String sql = "SELECT * " +
                "FROM SERVICE_CHECK_LOG " +
                "WHERE SERVICE_CHECK_LOG.id = ?";
        return (ServiceCheckLog) jdbcTemplate.queryForObject(sql, new ServiceCheckLogRowMapper(), id);
    }

    @Override
    public List<ServiceCheckLog> getServiceCheckLogsForCheckLog(long checkLogId) {
        String sql = "SELECT * " +
                "FROM SERVICE_CHECK_LOG " +
                "WHERE SERVICE_CHECK_LOG.check_log_id = ?";
        return (List<ServiceCheckLog>) jdbcTemplate.query(sql, new ServiceCheckLogRowMapper(), checkLogId);
    }

    public class ServiceCheckLogRowMapper implements RowMapper {

        @Override
        public ServiceCheckLog mapRow(ResultSet resultSet, int i) throws SQLException {
            return new ServiceCheckLog(
                    resultSet.getLong("id"),
                    resultSet.getLong("check_log_id"),
                    resultSet.getString("service_name"),
                    Formatter.convertDatabaseDateTime(resultSet.getString("date"))
            );
        }
    }

}
