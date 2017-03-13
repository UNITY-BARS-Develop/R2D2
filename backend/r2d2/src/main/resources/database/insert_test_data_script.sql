-- create services with parameters
INSERT INTO SERVICE (service_type_id, service_status_id, name, id)
    VALUES  ('WEB', 'ACTIVE', 'Service 1', '1'),
            ('WEB', 'ACTIVE', 'Service 2', '2'),
            ('WEB', 'DELETED', 'Service 3', '3'),
            ('SQL', 'ACTIVE', 'Service 4', '4'),
            ('SQL', 'PAUSED', 'Service 5', '5'),
            ('SQL', 'DELETED', 'Service 6', '6');

INSERT INTO SERVICE_TYPE_PARAMETER_VALUES (service_type_parameter_id, service_id, value, id)
    VALUES
        (2, '1', 'https://corplightdev.unity-bars.com:4443', 6),
        (2, '2', 'https://corplightdev.unity-bars.com:4443/Bars.API.Web.Client/api/v1/public/version', 5),
        (2, '3', 'https://corplightdev.unity-bars.com:4443', 4),
        (1, '4', 'Server=myServerAddress;Database=myDataBase;Trusted_Connection=True;', 3),
        (1, '5', 'Server=myServerAddress;Database=myDataBase;Trusted_Connection=True;', 2),
        (1, '6', 'Server=myServerAddress;Database=myDataBase;Trusted_Connection=True;', 1);

-- create tasks with values
INSERT INTO TASK (task_type_id, service_id, name, expected_value, id)
    VALUES  ('SQLRequest', '4', 'Task 1', 'some expected value 1', '3'),
            ('JSON', '2' ,'Task Json' , 404, '2'),
            ('StatusCode', '2' ,'Task StatusCode' ,200, '4'),
            ('StatusCode', '1', 'Task 1', 200, '1');

INSERT INTO TASK_FIELD_VALUE ( task_type_field_id, task_id, value, id )
    VALUES  ( 7, '2', 'Content-Type:application/json', 7 ),
            ( 7, '2', 'key:AAAAAAAA', 6 ),
            ( 5, '2', 'GET', 5 ),
            ( 3, '2', 'StatusCode', 3 ),
            ( 1, '3', 'SELECT * FROM tablename', 1 ),
            ( 2, '3', 'Oracle', 2 ),
            ( 4, '1', 'GET', 4 ),
            ( 5, '4', 'GET', 8 );

-- create logs
INSERT INTO CHECK_LOG (date,id)
    VALUES   ('14-12-2016 00:00:00', 1),
             ('15-12-2016 00:00:00', 2);

INSERT INTO SERVICE_CHECK_LOG (check_log_id,id,date,service_name)
    VALUES  (1,1,'14-12-2016 00:00:00','Service 1'),
            (1,2,'14-12-2016 00:00:00','Service 2'),
            (1,3,'14-12-2016 00:00:00','Service 3'),
            (2,4,'15-12-2016 00:00:00','Service 1'),
            (2,5,'15-12-2016 00:00:00','Service 2'),
            (2,6,'15-12-2016 00:00:00','Service 3');

INSERT INTO TASK_CHECK_LOG ( check_status_id, service_check_log_id, id, date, result_value, expected_value, task_type, task_name )
    VALUES  ( 'ERROR', 1, 1, '14-12-2016 00:00:00', 401, 200, 'JSON', 'Task 1. S1' ),
            ( 'SUCCESS', 2, 2, '14-12-2016 00:00:00', 200, 200, 'StatusCode', 'Task 1.S2' ),
            ( 'SUCCESS', 3, 3, '14-12-2016 00:00:00', 'OK', 'OK', 'JSON', 'Task 1.S3' ),
            ( 'SUCCESS', 1, 4, '15-12-2016 00:00:00', 200, 200, 'JSON', 'Task 1. S1' ),
            ( 'ERROR', 2, 5, '15-12-2016 00:00:00', 500, 200, 'StatusCode', 'Task 1.S2' ),
            ( 'ERROR', 3, 6, '15-12-2016 00:00:00', 'ERROR', 'OK', 'JSON', 'Task 1.S3' );


INSERT INTO RECIPIENT ( id, name, email )
    VALUES
        ( 1, 'Test Test', 'r2d2.monitorbot@gmail.com'),
        ( 2, 'Oleg Nestyuk', 'oleg.nestyuk@unity-bars.com');