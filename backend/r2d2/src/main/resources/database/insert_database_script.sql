INSERT INTO DICTIONARY_REQUEST_METHOD (name)
    VALUES
        ('PUT'),
        ('DELETE'),
        ('POST'),
        ('GET');

INSERT INTO DICTIONARY_DATABASE_TYPE(name)
    VALUES
        ('Oracle'),
        ('MySql');

INSERT INTO CHECK_STATUS (id, description)
    VALUES
        ('PERFORMED', NULL),
        ('SUCCESS', NULL),
        ('ERROR', NULL),
        ('UNEXPECTED_ERROR', NULL);

INSERT INTO SERVICE_STATUS (id, description)
    VALUES
        ('ACTIVE', NULL),
        ('PAUSED', NULL),
        ('DELETED', NULL);

INSERT INTO SERVICE_TYPE (id, description)
    VALUES
        ('WEB', NULL),
        ('SQL', NULL);

INSERT INTO TASK_TYPE (service_type_id, id)
    VALUES
        ('WEB', 'JSON'),
        ('WEB', 'StatusCode'),
        ('SQL', 'SQLRequest');

INSERT INTO TASK_TYPE_FIELD ( task_type_id, format, count, name, id )
    VALUES
        ('SQLRequest', NULL, 1, 'Request', 1 ),
        ('SQLRequest', NULL, 1, 'Database type', 2 ),
        ('JSON', NULL, 1, 'JSON field name', 3 ),
        ('StatusCode','{name}:{value}',0,'HeaderItem',7),
        ('JSON','{name}:{value}',0,'HeaderItem',6),
        ('StatusCode',NULL,1,'Request method',5),
        ('JSON',NULL,1,'Request method',4);


INSERT INTO SERVICE_TYPE_PARAMETER (service_type_id, name, id)
    VALUES
        ('SQL', 'Connection string', 1),
        ('WEB', 'url', 2 );

INSERT INTO SETTINGS ( description, value, [key] )
    VALUES
        ( NULL, 'true', 'schedule.enable' ),
        ( NULL, 'false', 'sender.mail.send_if_success' ),
        ( 'Period in miliseconds', 900000, 'schedule.period' ),
        ( NULL, 'true', 'smtp.tlsenable' ),
        ( NULL, 587, 'smtp.port' ),
        ( NULL, 'r2d2d2r2', 'smtp.password' ),
        ( NULL, 'r2d2.monitorbot@gmail.com', 'smtp.username' ),
        ( NULL, 'smtp.gmail.com', 'smtp.host' ),
        ( NULL, 'R2D2 - Звіт', 'sender.mail.subject' );
