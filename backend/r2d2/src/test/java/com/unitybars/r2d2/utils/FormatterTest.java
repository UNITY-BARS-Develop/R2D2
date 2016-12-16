package com.unitybars.r2d2.utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
public class FormatterTest {

    @Test
    public void formatDatabaseDateTime() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2005, 11, 24, 5, 59, 0);
        Date date = calendar.getTime();
        String formattedDate = Formatter.formatDatabaseDateTime(date);
        assertEquals("2005-12-24 05:59:00", formattedDate);
    }

    @Test
    public void convertDatabaseDateTime() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1985, 0, 31, 0, 25, 59);
        Date date = new Date (1000 * (calendar.getTime().getTime()/ 1000));
        Date convertedDate = Formatter.convertDatabaseDateTime("1985-01-31 00:25:59");
        assertEquals(date.getTime(), convertedDate.getTime());
    }

}