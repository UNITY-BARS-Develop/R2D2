package com.unitybars.r2d2.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.File;
import java.util.Arrays;

/**
 * Created by oleg.nestyuk
 * Date: 12-Dec-16.
 */
public class TestDatabase extends DriverManagerDataSource {
    private String databaseFile;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void shutdown() {
        logger.info("Test database shutdown");
        deleteDatabaseFile();
    }

    private void deleteDatabaseFile() {
        if (databaseFile != null && databaseFile.length() > 0) {
            try {
                File file = new File(databaseFile);
                if (file.delete()) {
                    logger.info("Database file " + databaseFile + " deleted");
                } else {
                    logger.error("Database file " + databaseFile + " delete operation failed");
                }
            } catch (Exception e) {
                logger.error("Database file " + databaseFile + " delete operation failed. ");
                logger.error(e.getMessage() + " Stack trace: " + Arrays.toString(e.getStackTrace()));
            }
        }
    }

    public void setDatabaseFile(String databaseFile) {
        this.databaseFile = databaseFile;
    }
}
