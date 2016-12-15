package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.AbstractDaoTest;
import com.unitybars.r2d2.dao.CheckLogDao;
import com.unitybars.r2d2.entity.CheckLog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
public class SqliteCheckLogDaoTest extends AbstractDaoTest {
    @Autowired
    private CheckLogDao checkLogDao;

    @Test
    public void testInit() {
        assertNotNull(checkLogDao);
    }

    @Test
    public void createCheckLog() throws Exception {
        Date date = new Date();
        CheckLog checkLog = new CheckLog();
        checkLog.setDate(date);
        long startId = 3;
        for (int i = 0; i < 10; i++) {
            long id = checkLogDao.insertCheckLog(checkLog);
            assertEquals(startId + i, id);
        }
    }

    @Test
    public void getCheckLogById() throws Exception {
        CheckLog checkLog = checkLogDao.getCheckLogById(1);
        assertNotNull(checkLog);
        assertNotNull(checkLog.getDate());
        assertEquals(1, checkLog.getId());
    }

    @Test
    public void getAllCheckLog() throws Exception {
        List<CheckLog> checkLogList = checkLogDao.getAllCheckLogs();
        assertNotNull(checkLogList);
        assertEquals(2, checkLogList.size());
    }

}