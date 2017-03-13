package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.TaskTypeFieldDao;
import com.unitybars.r2d2.entity.TaskTypeField;
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
 * Date: 07-Mar-17.
 */
@Repository
@Qualifier("sqliteData")
public class SqliteTaskTypeField implements TaskTypeFieldDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<TaskTypeField> getAllTaskTypeFieldsByTaskTypeId(TaskTypeId taskTypeId) {
        String sql = "SELECT * FROM TASK_TYPE_FIELD " +
                "WHERE task_type_id = ?";
        return jdbcTemplate.query(sql, new TaskTypeFieldRowMapper(), taskTypeId.toString());
    }

    class TaskTypeFieldRowMapper implements RowMapper {

        @Override
        public TaskTypeField mapRow(ResultSet resultSet, int i) throws SQLException {
            return new TaskTypeField(
                    resultSet.getInt("id"),
                    TaskTypeId.getTaskType(resultSet.getString("task_type_id")),
                    resultSet.getString("name"),
                    resultSet.getInt("count"),
                    resultSet.getString("format")
            );
        }
    }
}
