package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.TaskTypeDao;
import com.unitybars.r2d2.entity.ServiceType;
import com.unitybars.r2d2.entity.TaskTypeId;
import com.unitybars.r2d2.entity.response.TaskTypeJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by oleg.nestyuk
 * Date: 07-Mar-17.
 */
@Repository
@Qualifier("sqliteData")
public class SqliteTaskTypeDao implements TaskTypeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TaskTypeJson getTaskTypeById(TaskTypeId taskTypeId) {
        String sql = "SELECT * FROM TASK_TYPE " +
                "WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new TaskTypeRowMapper(), taskTypeId.toString());
    }

    class TaskTypeRowMapper implements RowMapper<TaskTypeJson> {

        @Override
        public TaskTypeJson mapRow(ResultSet resultSet, int i) throws SQLException {
            return new TaskTypeJson(
                    TaskTypeId.getTaskType(resultSet.getString("id")),
                    ServiceType.getServiceType(resultSet.getString("service_type_id")),
                    resultSet.getString("description"));
        }
    }

}
