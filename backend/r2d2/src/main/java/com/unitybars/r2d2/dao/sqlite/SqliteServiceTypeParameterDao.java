package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.ServiceTypeParameterDao;
import com.unitybars.r2d2.entity.ServiceType;
import com.unitybars.r2d2.entity.ServiceTypeParameter;
import com.unitybars.r2d2.entity.response.ServiceTypeParameterJson;
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
 * Date: 06-Mar-17.
 */
@Repository
@Qualifier("sqliteData")
public class SqliteServiceTypeParameterDao implements ServiceTypeParameterDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ServiceTypeParameter> getByServiceType(ServiceType serviceType) {
        String sql = "SELECT * " +
                "FROM SERVICE_TYPE_PARAMETER " +
                "WHERE service_type_id = ?";
        return jdbcTemplate.query(sql, new ServiceTypeParameterRowMapper(), serviceType.toString());
    }

    @Override
    public List<ServiceTypeParameterJson> getAllServiceTypeParameters() {
        String sql = "SELECT * FROM SERVICE_TYPE_PARAMETER";
        return jdbcTemplate.query(sql, new FullServiceTypeRowMapper());
    }


    class FullServiceTypeRowMapper implements RowMapper {

        @Override
        public ServiceTypeParameterJson mapRow(ResultSet resultSet, int i) throws SQLException {
            return new ServiceTypeParameterJson(
                    ServiceType.getServiceType(resultSet.getString("service_type_id")),
                    ServiceTypeParameter.getServiceTypeParameter(resultSet.getString("id")),
                    resultSet.getString("name")
            );
        }
    }


    class ServiceTypeParameterRowMapper implements RowMapper {

        @Override
        public ServiceTypeParameter mapRow(ResultSet resultSet, int i) throws SQLException {
            return ServiceTypeParameter.getServiceTypeParameter(resultSet.getString("name"));
        }
    }
}
