package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.ServiceDao;
import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.ServiceStatus;
import com.unitybars.r2d2.entity.ServiceType;
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
 * Date: 08-Dec-16.
 */
@Repository
@Qualifier("sqliteData")
public class SqliteServiceDao implements ServiceDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Service> getAllServices() {
        String sql =
                "SELECT " +
                        "SERVICE.*, " +
                        "SERVICE_STATUS.id as service_status, " +
                        "SERVICE_TYPE.id as service_type " +
                        "FROM SERVICE " +
                        "JOIN SERVICE_TYPE on SERVICE.service_type_id=SERVICE_TYPE.id " +
                        "JOIN SERVICE_STATUS on SERVICE.service_status_id=SERVICE_STATUS.id";
        return (List<Service>) jdbcTemplate.query(sql, new ServiceRowMapper());
    }

    @Override
    public Service getServiceById(String id) {
        String sql =
                "SELECT " +
                        "SERVICE.*, " +
                        "SERVICE_STATUS.id as service_status, " +
                        "SERVICE_TYPE.id as service_type " +
                        "FROM SERVICE " +
                        "JOIN SERVICE_TYPE on SERVICE.service_type_id=SERVICE_TYPE.id " +
                        "JOIN SERVICE_STATUS on SERVICE.service_status_id=SERVICE_STATUS.id " +
                        "WHERE " +
                        "SERVICE.id = ?";
        return (Service) jdbcTemplate.queryForObject(sql, new ServiceRowMapper(), id);
    }

    @Override
    public void create(Service service) {
        String sql = "INSERT INTO SERVICE (id, service_type_id, service_status_id, name) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, service.getId(), service.getServiceType(), service.getServiceStatus(), service.getName());
    }

    @Override
    public void update(Service service) {
        String sql = "UPDATE SERVICE " +
                "SET name = ?, service_status_id = ?" +
                "WHERE id = ?";
        jdbcTemplate.update(sql, service.getName(), service.getServiceStatus(), service.getId());
    }

    @Override
    public void setServiceStatus(String id, ServiceStatus status) {
        String sql = "UPDATE SERVICE " +
                "SET service_status_id = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql, status, id);
    }

    public class ServiceRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Service(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    ServiceType.getServiceType(resultSet.getString("service_type")),
                    ServiceStatus.getServiceStatus(resultSet.getString("service_status"))
            );
        }
    }
}
