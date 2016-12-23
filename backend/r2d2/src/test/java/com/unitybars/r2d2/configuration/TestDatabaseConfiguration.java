package com.unitybars.r2d2.configuration;

import com.unitybars.r2d2.database.TestDatabaseBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * Created by oleg.nestyuk
 * Date: 12-Dec-16.
 */
@Configuration
@Profile("test")
public class TestDatabaseConfiguration {

    @Bean(destroyMethod = "shutdown")
    @Primary
    public DataSource testDataSource() {
        return new TestDatabaseBuilder()
                .addSqlScript("/database/create_database_script.sql")
                .addSqlScript("/database/insert_database_script.sql")
                .addSqlScript("/database/insert_test_data_script.sql")
                .build();
    }
}
