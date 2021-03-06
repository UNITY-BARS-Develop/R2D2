package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.TaskDao;
import com.unitybars.r2d2.entity.Task;
import com.unitybars.r2d2.entity.TaskTypeId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
@Repository
@Qualifier("sqliteData")
public class SqliteTaskDao implements TaskDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Task> getAllTasks() {
        String sql = "SELECT TASK.*, TASK_TYPE.id as task_type_name " +
                "FROM TASK " +
                "JOIN TASK_TYPE on TASK.task_type_id=TASK_TYPE.id";
        return jdbcTemplate.query(sql, new TaskRowMapper());
    }

    @Override
    public Task getTaskById(String id) {
        String sql = "SELECT TASK.*, TASK_TYPE.id as task_type_name " +
                "FROM TASK " +
                "JOIN TASK_TYPE on TASK.task_type_id=TASK_TYPE.id " +
                "WHERE TASK.id = ?";
        return (Task) jdbcTemplate.queryForObject(sql, new TaskRowMapper(), id);
    }

    @Override
    public List<Task> getTasksForService(String serviceId) {
        String sql = "SELECT TASK.*, TASK_TYPE.id as task_type_name " +
                "FROM TASK " +
                "JOIN TASK_TYPE on TASK.task_type_id=TASK_TYPE.id " +
                "WHERE TASK.service_id = ?";
        return jdbcTemplate.query(sql, new TaskRowMapper(), serviceId);
    }

    @Override
    public void create(Task task) {
        String sql = "INSERT INTO TASK (id, task_type_id, service_id, name, expected_value)" +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, task.getId(), task.getTaskTypeId(), task.getServiceId(), task.getName(),
                task.getExpectedValue());
    }

    @Override
    public void update(Task task) {
        String sql = "UPDATE TASK " +
                "SET expected_value = ?, name = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql, task.getExpectedValue(), task.getName(), task.getId());
    }

    @Override
    public void delete(String taskId) {
        String sql = "DELETE FROM TASK WHERE id = ?";
        jdbcTemplate.update(sql, taskId);
    }

    public class TaskRowMapper implements RowMapper {

        @Override
        public Task mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Task(
                    resultSet.getString("id"),
                    resultSet.getString("service_id"),
                    TaskTypeId.getTaskType(resultSet.getString("task_type_name")),
                    resultSet.getString("expected_value"),
                    resultSet.getString("name")
            );
        }
    }
}
