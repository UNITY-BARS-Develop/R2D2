package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.ServiceTypeParameterValueDao;
import com.unitybars.r2d2.entity.ServiceTypeParameter;
import com.unitybars.r2d2.entity.ServiceTypeParameterValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by oleg.nestyuk
 * Date: 13-Dec-16.
 */
@Repository
@Qualifier("sqliteData")
public class SqliteServiceTypeParameterValueDao implements ServiceTypeParameterValueDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ServiceTypeParameterValue> getAllServiceTypeParameterValues() {
        String sql =
                "SELECT  " +
                        "SERVICE_TYPE_PARAMETER_VALUES.* , SERVICE_TYPE_PARAMETER.name as service_type_parameter_name " +
                        "FROM SERVICE_TYPE_PARAMETER_VALUES " +
                        "JOIN SERVICE_TYPE_PARAMETER ON SERVICE_TYPE_PARAMETER_VALUES.service_type_parameter_id=SERVICE_TYPE_PARAMETER.id ";
        return jdbcTemplate.query(sql, new ServiceTypeParameterValueRowMapper());
    }

    @Override
    public List<ServiceTypeParameterValue> getServiceTypeParameterValuesForService(String serviceId) {
        String sql =
                "SELECT  " +
                        "SERVICE_TYPE_PARAMETER_VALUES.* , SERVICE_TYPE_PARAMETER.name as service_type_parameter_name " +
                        "FROM SERVICE_TYPE_PARAMETER_VALUES " +
                        "JOIN SERVICE_TYPE_PARAMETER ON SERVICE_TYPE_PARAMETER_VALUES.service_type_parameter_id=SERVICE_TYPE_PARAMETER.id " +
                        "WHERE SERVICE_TYPE_PARAMETER_VALUES.service_id = ?";
        return jdbcTemplate.query(sql, new ServiceTypeParameterValueRowMapper(), serviceId);
    }

    @Override
    public void create(Map<ServiceTypeParameter, String> parameters, String serviceId) {
        List<String[]> parametersList = new ArrayList<>();
        for (Map.Entry<ServiceTypeParameter, String> entry : parameters.entrySet()) {
            parametersList.add(new String[]{entry.getKey().toString(), entry.getValue()});
        }
        String sql = "INSERT INTO SERVICE_TYPE_PARAMETER_VALUES (service_type_parameter_id, service_id, value) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, parametersList.get(i)[0]);
                preparedStatement.setString(2, serviceId);
                preparedStatement.setString(3, parametersList.get(i)[1]);
            }

            @Override
            public int getBatchSize() {
                return parametersList.size();
            }
        });
    }

    public class ServiceTypeParameterValueRowMapper implements RowMapper {
        @Override
        public ServiceTypeParameterValue mapRow(ResultSet resultSet, int i) throws SQLException {
            return new ServiceTypeParameterValue(
                    resultSet.getInt("id"),
                    resultSet.getString("service_id"),
                    ServiceTypeParameter.getServiceTypeParameter(resultSet.getString("service_type_parameter_name")),
                    resultSet.getString("value")
            );
        }
    }
}