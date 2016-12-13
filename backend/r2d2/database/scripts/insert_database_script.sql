INSERT INTO DICTIONARY_REQUEST_METHOD (name)
    VALUES ('PUT'),('DELETE'),('POST'),('GET');
    
INSERT INTO DICTIONARY_DATABASE_TYPE(name)
    VALUES ('Oracle'),('MySql');

INSERT INTO CHECK_STATUS (name)
    VALUES ('PERFORMED'),('SUCCESS'),('ERROR'),('UNEXPECTED_ERROR');
    
INSERT INTO SERVICE_STATUS (status)
    VALUES ('ACTIVE'),('PAUSED'),('DELETED');
    
INSERT INTO SERVICE_TYPE (name)
    VALUES ('WEB'),('SQL');
    
INSERT INTO TASK_TYPE (service_type_id, name, id)
    VALUES ( 1, 'JSON', 1), (1, 'Status Code', 2), (2, 'SQL Request', 3);

INSERT INTO TASK_TYPE_FIELD ( task_type_id, format, count, name, id )
    VALUES ( 3, NULL, 1, 'Request', 1 ), ( 3, NULL, 1, 'Database type', 2 ), ( 1, NULL, 1, 'JSON field name', 3 );
