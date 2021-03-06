DROP TABLE IF EXISTS SERVICE_TYPE_PARAMETER_VALUES;

DROP TABLE IF EXISTS SERVICE_TYPE_PARAMETER;

DROP TABLE IF EXISTS TASK_FIELD_VALUE;

DROP TABLE IF EXISTS TASK_TYPE_FIELD;

DROP TABLE IF EXISTS TASK;

DROP TABLE IF EXISTS TASK_TYPE;

DROP TABLE IF EXISTS SERVICE;

DROP TABLE IF EXISTS SERVICE_TYPE;

DROP TABLE IF EXISTS SERVICE_STATUS;

DROP TABLE IF EXISTS SETTINGS;

DROP TABLE IF EXISTS DICTIONARY_DATABASE_TYPE;

DROP TABLE IF EXISTS DICTIONARY_REQUEST_METHOD;

DROP TABLE IF EXISTS TASK_CHECK_LOG;

DROP TABLE IF EXISTS CHECK_STATUS;

DROP TABLE IF EXISTS SERVICE_CHECK_LOG;

DROP TABLE IF EXISTS CHECK_LOG;

DROP TABLE IF EXISTS RECIPIENT;

CREATE TABLE SERVICE_TYPE (
    id          STRING PRIMARY KEY
                       UNIQUE
                       NOT NULL,
    description TEXT
);

CREATE TABLE SERVICE_STATUS (
    id          STRING PRIMARY KEY
                       UNIQUE
                       NOT NULL,
    description TEXT
);

CREATE TABLE DICTIONARY_DATABASE_TYPE (
    name STRING NOT NULL
);

CREATE TABLE DICTIONARY_REQUEST_METHOD (
    name STRING NOT NULL
);

CREATE TABLE CHECK_LOG (
    id   INTEGER  PRIMARY KEY AUTOINCREMENT
                  UNIQUE
                  NOT NULL,
    date DATETIME NOT NULL
);

CREATE TABLE CHECK_STATUS (
    id          STRING PRIMARY KEY
                       UNIQUE
                       NOT NULL,
    description TEXT
);

CREATE TABLE RECIPIENT (
    id    INTEGER PRIMARY KEY AUTOINCREMENT,
    name  VARCHAR,
    email VARCHAR NOT NULL
);

CREATE TABLE SERVICE (
    id                VARCHAR PRIMARY KEY
                              UNIQUE
                              NOT NULL,
    name              STRING  NOT NULL,
    service_status_id STRING  REFERENCES SERVICE_STATUS (id) ON DELETE NO ACTION
                                                             ON UPDATE NO ACTION,
    service_type_id   STRING  REFERENCES SERVICE_TYPE (id) ON DELETE NO ACTION
                                                           ON UPDATE NO ACTION
);

CREATE TABLE SERVICE_CHECK_LOG (
    service_name STRING,
    date         DATETIME,
    id           INTEGER  PRIMARY KEY AUTOINCREMENT
                          UNIQUE
                          NOT NULL,
    check_log_id INTEGER  REFERENCES CHECK_LOG (id) ON DELETE NO ACTION
                                                    ON UPDATE NO ACTION
);

CREATE TABLE SERVICE_TYPE_PARAMETER (
    id              VARCHAR PRIMARY KEY,
    name            STRING  NOT NULL,
    service_type_id STRING  REFERENCES SERVICE_TYPE (id) ON DELETE NO ACTION
                                                         ON UPDATE NO ACTION
);

CREATE TABLE SERVICE_TYPE_PARAMETER_VALUES (
    id                        INTEGER PRIMARY KEY AUTOINCREMENT
                                      UNIQUE
                                      NOT NULL,
    value                     TEXT    NOT NULL,
    service_id                VARCHAR REFERENCES SERVICE (id) ON DELETE NO ACTION
                                                              ON UPDATE NO ACTION,
    service_type_parameter_id VARCHAR REFERENCES SERVICE_TYPE_PARAMETER (id) ON DELETE CASCADE
                                                                             ON UPDATE CASCADE
);

CREATE TABLE SETTINGS (
    [key]       STRING NOT NULL,
    value       STRING NOT NULL,
    description TEXT
);

CREATE TABLE TASK (
    id             VARCHAR PRIMARY KEY
                           UNIQUE
                           NOT NULL,
    expected_value TEXT    NOT NULL,
    name           STRING  NOT NULL,
    service_id     VARCHAR REFERENCES SERVICE (id) ON DELETE NO ACTION
                                                   ON UPDATE NO ACTION,
    task_type_id   STRING  REFERENCES TASK_TYPE (id) ON DELETE NO ACTION
                                                     ON UPDATE NO ACTION
);

CREATE TABLE TASK_CHECK_LOG (
    task_name            STRING,
    task_type            STRING,
    expected_value       TEXT,
    result_value         TEXT,
    date                 DATETIME,
    id                   INTEGER  PRIMARY KEY AUTOINCREMENT
                                  UNIQUE
                                  NOT NULL,
    service_check_log_id INTEGER  REFERENCES SERVICE_CHECK_LOG (id) ON DELETE NO ACTION
                                                                    ON UPDATE NO ACTION,
    check_status_id      STRING   REFERENCES CHECK_STATUS (id) ON DELETE NO ACTION
                                                               ON UPDATE NO ACTION,
    comment              VARCHAR
);

CREATE TABLE TASK_FIELD_VALUE (
    id                 INTEGER PRIMARY KEY AUTOINCREMENT
                               UNIQUE
                               NOT NULL,
    value              TEXT    NOT NULL,
    task_id            VARCHAR REFERENCES TASK (id) ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
    task_type_field_id INTEGER REFERENCES TASK_TYPE_FIELD (id) ON DELETE CASCADE
                                                               ON UPDATE CASCADE
);

CREATE TABLE TASK_TYPE (
    id              STRING PRIMARY KEY
                           UNIQUE
                           NOT NULL,
    description     STRING,
    service_type_id STRING REFERENCES SERVICE_TYPE (id) ON DELETE NO ACTION
                                                        ON UPDATE NO ACTION
);

CREATE TABLE TASK_TYPE_FIELD (
    id           INTEGER PRIMARY KEY AUTOINCREMENT
                         UNIQUE
                         NOT NULL,
    name         STRING  NOT NULL,
    count        INTEGER NOT NULL
                         DEFAULT 1,
    format       TEXT,
    task_type_id STRING  REFERENCES TASK_TYPE (id) ON DELETE NO ACTION
                                                   ON UPDATE NO ACTION
);
