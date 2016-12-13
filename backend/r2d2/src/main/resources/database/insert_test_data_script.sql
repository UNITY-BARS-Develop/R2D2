-- create services with parameters
INSERT INTO SERVICE ( id, name, service_type_id, service_status_id)  VALUES (1, "Service 1", 'WEB', 'ACTIVE');
INSERT INTO SERVICE ( id, name, service_type_id, service_status_id)  VALUES (2, "Service 2", 'WEB', 'PAUSED');
INSERT INTO SERVICE ( id, name, service_type_id, service_status_id)  VALUES (3, "Service 3", 'WEB', 'DELETED');
INSERT INTO SERVICE ( id, name, service_type_id, service_status_id)  VALUES (4, "Service 4", 'SQL', 'ACTIVE');
INSERT INTO SERVICE ( id, name, service_type_id, service_status_id)  VALUES (5, "Service 5", 'SQL', 'PAUSED');
INSERT INTO SERVICE ( id, name, service_type_id, service_status_id)  VALUES (6, "Service 6", 'SQL', 'DELETED');

INSERT INTO SERVICE_TYPE_PARAMETER_VALUES (service_type_parameter_id, service_id, value, id)
    VALUES
        (1, 6, 'Server=myServerAddress;Database=myDataBase;Trusted_Connection=True;', 1),
        (1, 5, 'Server=myServerAddress;Database=myDataBase;Trusted_Connection=True;', 2),
        (1, 4, 'Server=myServerAddress;Database=myDataBase;Trusted_Connection=True;', 3),
        (2, 3, 'https://corplightdev.unity-bars.com:4443/', 4),
        (2, 2, 'https://corplightdev.unity-bars.com:4443/', 5),
        (2, 1, 'https://corplightdev.unity-bars.com:4443/', 6);