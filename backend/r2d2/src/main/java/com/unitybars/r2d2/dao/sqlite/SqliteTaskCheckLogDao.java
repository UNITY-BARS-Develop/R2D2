package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.TaskCheckLogDao;
import com.unitybars.r2d2.entity.CheckStatus;
import com.unitybars.r2d2.entity.TaskCheckLog;
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
public class SqliteTaskCheckLogDao implements TaskCheckLogDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long insertTaskCheckLog(TaskCheckLog taskCheckLog) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO TASK_CHECK_LOG (task_name, date, task_type, expected_value, result_value , service_check_log_id, check_status_id) " +
                "  VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, taskCheckLog.getTaskName());
            ps.setString(2, Formatter.formatDatabaseDateTime(taskCheckLog.getDate()));
            ps.setString(3, taskCheckLog.getTaskType());
            ps.setString(4, taskCheckLog.getExpectedValue());
            ps.setString(5, taskCheckLog.getResultValue());
            ps.setLong(6, taskCheckLog.getServiceCheckLogId());
            ps.setString(7, taskCheckLog.getCheckStatus().name());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public TaskCheckLog getTaskCheckLogById(int id) {
        String sql = "SELECT * " +
                "FROM TASK_CHECK_LOG " +
                "WHERE TASK_CHECK_LOG.id = ?";
        return (TaskCheckLog) jdbcTemplate.queryForObject(sql, new TaskCheckLogRowMapper(), id);
    }

    @Override
    public List<TaskCheckLog> getTaskCheckLogsForServiceCheckLog(int serviceCheckLogId) {
        String sql = "SELECT * " +
                "FROM TASK_CHECK_LOG " +
                "WHERE TASK_CHECK_LOG.service_check_log_id = ?";
        return (List<TaskCheckLog>) jdbcTemplate.query(sql, new TaskCheckLogRowMapper(), serviceCheckLogId);
    }

    public class TaskCheckLogRowMapper implements RowMapper {

        @Override
        public TaskCheckLog mapRow(ResultSet resultSet, int i) throws SQLException {
            return new TaskCheckLog(
                    resultSet.getLong("id"),
                    resultSet.getString("task_name"),
                    resultSet.getString("task_type"),
                    resultSet.getString("expected_value"),
                    resultSet.getString("result_value"),
                    Formatter.convertDatabaseDateTime(resultSet.getString("date")),
                    CheckStatus.getCheckStatus(resultSet.getString("check_status_id")),
                    resultSet.getLong("service_check_log_id"));
        }
    }
}
