-- create services with parameters
INSERT INTO SERVICE (service_type_id, service_status_id, name, id) VALUES ('WEB', 'ACTIVE', 'Service 1', 1);
INSERT INTO SERVICE (service_type_id, service_status_id, name, id) VALUES ('WEB', 'PAUSED', 'Service 2', 2);
INSERT INTO SERVICE (service_type_id, service_status_id, name, id) VALUES ('WEB', 'DELETED', 'Service 3', 3);
INSERT INTO SERVICE (service_type_id, service_status_id, name, id) VALUES ('SQL', 'ACTIVE', 'Service 4', 4);
INSERT INTO SERVICE (service_type_id, service_status_id, name, id) VALUES ('SQL', 'PAUSED', 'Service 5', 5);
INSERT INTO SERVICE (service_type_id, service_status_id, name, id) VALUES ('SQL', 'DELETED', 'Service 6', 6);

INSERT INTO SERVICE_TYPE_PARAMETER_VALUES (service_type_parameter_id, service_id, value, id)
    VALUES
        (1, 6, 'Server=myServerAddress;Database=myDataBase;Trusted_Connection=True;', 1),
        (1, 5, 'Server=myServerAddress;Database=myDataBase;Trusted_Connection=True;', 2),
        (1, 4, 'Server=myServerAddress;Database=myDataBase;Trusted_Connection=True;', 3),
        (2, 3, 'https://corplightdev.unity-bars.com:4443', 4),
        (2, 2, 'https://corplightdev.unity-bars.com:4443', 5),
        (2, 1, 'https://corplightdev.unity-bars.com:4443', 6);