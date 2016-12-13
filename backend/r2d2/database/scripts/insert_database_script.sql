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

INSERT INTO TASK_TYPE (service_type_id, name, id)
    VALUES
        ('WEB', 'JSON', 1),
        ('WEB', 'Status Code', 2),
        ('SQL', 'SQL Request', 3);

INSERT INTO TASK_TYPE_FIELD ( task_type_id, format, count, name, id )
    VALUES
        (3, NULL, 1, 'Request', 1 ),
        (3, NULL, 1, 'Database type', 2 ),
        (1, NULL, 1, 'JSON field name', 3 );

INSERT INTO SERVICE_TYPE_PARAMETER (service_type_id, name, id)
    VALUES
        ('SQL', 'Connection string', 1),
        ('WEB', 'url', 2 );
