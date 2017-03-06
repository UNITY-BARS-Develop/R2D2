package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.TaskFieldValueDao;
import com.unitybars.r2d2.entity.TaskFieldValue;
import com.unitybars.r2d2.entity.TaskType;
import com.unitybars.r2d2.entity.TaskTypeField;
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
public class SqliteTaskFieldValueDao implements TaskFieldValueDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<TaskFieldValue> getAllTaskFieldValues() {
        String sql =
                "SELECT " +
                        "TASK_FIELD_VALUE.*, TASK_TYPE_FIELD.count, TASK_TYPE_FIELD.task_type_id, TASK_TYPE_FIELD.name, TASK_TYPE_FIELD.format " +
                        "FROM TASK_FIELD_VALUE " +
                        "JOIN TASK_TYPE_FIELD on TASK_FIELD_VALUE.task_type_field_id = TASK_TYPE_FIELD.id";
        return jdbcTemplate.query(sql, new TaskFieldValueRowMapper());
    }

    @Override
    public List<TaskFieldValue> getTaskFieldValuesForTask(int taskId) {
        String sql =
                "SELECT TASK_FIELD_VALUE.*, TASK_TYPE_FIELD.count, TASK_TYPE_FIELD.task_type_id, TASK_TYPE_FIELD.name, TASK_TYPE_FIELD.format " +
                        "FROM TASK_FIELD_VALUE " +
                        "JOIN TASK_TYPE_FIELD on TASK_FIELD_VALUE.task_type_field_id = TASK_TYPE_FIELD.id " +
                        "WHERE TASK_FIELD_VALUE.task_id = ?";
        return jdbcTemplate.query(sql, new TaskFieldValueRowMapper(), taskId);
    }

    public class TaskFieldValueRowMapper implements RowMapper {

        @Override
        public TaskFieldValue mapRow(ResultSet resultSet, int i) throws SQLException {
            return new TaskFieldValue(
                    resultSet.getInt("id"),
                    resultSet.getString("task_id"),
                    new TaskTypeField(
                            resultSet.getInt("task_type_field_id"),
                            TaskType.getTaskType(resultSet.getString("task_type_id")),
                            resultSet.getString("name"),
                            resultSet.getInt("count"),
                            resultSet.getString("format")
                    ),
                    resultSet.getString("value")
            );
        }
    }
}