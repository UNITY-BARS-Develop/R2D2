package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.RecipientDao;
import com.unitybars.r2d2.entity.Recipient;
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
 * Date: 09-Mar-17.
 */
@Repository
@Qualifier("sqliteData")
public class SqliteRecipientDao implements RecipientDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Recipient> getAllRecipients() {
        String sql = "SELECT * " +
                "FROM RECIPIENT";
        return jdbcTemplate.query(sql, new RecipientRowMapper());
    }

    @Override
    public Recipient get(int recipientId) {
        String sql = "SELECT * " +
                "FROM RECIPIENT " +
                "WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new RecipientRowMapper(), recipientId);
    }

    @Override
    public void create(Recipient recipient) {
        String sql = "INSERT INTO RECIPIENT (email, name) " +
                "VALUES (?, ?)";
        jdbcTemplate.update(sql, recipient.getEmail(), recipient.getName());
    }

    @Override
    public void update(Recipient recipient) {
        String sql = "UPDATE RECIPIENT " +
                "SET name = ?, email = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql, recipient.getName(), recipient.getEmail(), recipient.getId());
    }

    @Override
    public void delete(int recipientId) {
        String sql = "DELETE FROM RECIPIENT " +
                "WHERE id = ?";
        jdbcTemplate.update(sql, recipientId);
    }

    class RecipientRowMapper implements RowMapper<Recipient> {

        @Override
        public Recipient mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Recipient(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email")
            );
        }
    }
}
