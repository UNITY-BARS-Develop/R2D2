package com.unitybars.r2d2.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
public class Formatter {
    public static final String databaseDatetimeFormat = "yyyy-MM-dd HH:mm:ss";
    private static Logger logger = LoggerFactory.getLogger(Formatter.class);

    public static String formatDatabaseDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(databaseDatetimeFormat, Locale.getDefault());
        return dateFormat.format(date);
    }

    public static String formatDateTime(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(databaseDatetimeFormat, Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    public static Date convertDatabaseDateTime(String datetime) {
        DateFormat format = new SimpleDateFormat(databaseDatetimeFormat, Locale.getDefault());
        try {
            return format.parse(datetime);
        } catch (ParseException e) {
            logger.error("Error happened when try parse datetime " + datetime);
            e.printStackTrace();
            return null;
        }
    }

    public static String convertThrowableToString(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
