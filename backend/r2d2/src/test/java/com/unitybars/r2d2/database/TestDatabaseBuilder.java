package com.unitybars.r2d2.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.util.ArrayList;

/**
 * Created by oleg.nestyuk
 * Date: 12-Dec-16.
 */
public class TestDatabaseBuilder {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String databaseFile = "database/test_database_" + System.nanoTime() + ".db";
    private final String driver = "org.sqlite.JDBC";
    private final String username = "";
    private final String password = "";

    private ArrayList<String> scripts;
    private TestDatabase database;

    public TestDatabaseBuilder() {
        this.scripts = new ArrayList<>();
        createDatabase();
    }

    private void createDatabase() {
        String databaseFileExample = this.databaseFile;
        database = new TestDatabase();
        database.setDriverClassName(driver);
        database.setUrl("jdbc:sqlite:" + databaseFileExample);
        database.setUsername(username);
        database.setPassword(password);
        database.setDatabaseFile(databaseFileExample);
        logger.debug("=============== Starting Embedded SQLite using these parameters ===============");
        logger.debug("database file : " + databaseFileExample);
        logger.debug("===============================================================================");
    }

    public TestDatabaseBuilder addSqlScript(String script) {
        scripts.add(script);
        return this;
    }

    private void populateScripts() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        for (String scriptFile : scripts) {
            Resource resource = resourceLoader.getResource(scriptFile);
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
            databasePopulator.execute(database);
        }
    }

    public TestDatabase build() {
        populateScripts();
        return database;
    }
}