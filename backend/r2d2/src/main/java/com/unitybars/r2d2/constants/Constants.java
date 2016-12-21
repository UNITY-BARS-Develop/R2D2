package com.unitybars.r2d2.constants;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
public class Constants {
    public static class TaskTypeFieldConstants{
        public static String HEADER = "header";
        public static String REQUEST_METHOD = "request method";
        public static String JSON_FIELD_NAME = "json field name";
        public static String DATABASE_TYPE = "database type";
        public static String REQUEST = "request";
    }

    public static class CheckPeriod{
        public static int DEFAULT_CHECK_PERIOD = 3600000;   // 1 hour
    }
}
