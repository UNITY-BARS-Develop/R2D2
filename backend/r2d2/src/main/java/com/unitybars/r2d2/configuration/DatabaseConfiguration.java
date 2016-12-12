package com.unitybars.r2d2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by oleg.nestyuk
 * Date: 09-Dec-16.
 */
@Configuration
public class DatabaseConfiguration {
    @Resource
    private Environment environment;

    @Bean
    public DataSource sqliteDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("sqlitedb.datasource.driver"));
        dataSource.setUrl(environment.getProperty("sqlitedb.datasource.url"));
        dataSource.setUsername(environment.getProperty("sqlitedb.datasource.username"));
        dataSource.setPassword(environment.getProperty("sqlitedb.datasource.password"));
        return dataSource;
    }

}
