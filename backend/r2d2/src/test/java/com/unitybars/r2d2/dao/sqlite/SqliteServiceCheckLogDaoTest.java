package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.AbstractDaoTest;
import com.unitybars.r2d2.dao.ServiceCheckLogDao;
import com.unitybars.r2d2.entity.ServiceCheckLog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
public class SqliteServiceCheckLogDaoTest extends AbstractDaoTest {
    @Autowired
    private ServiceCheckLogDao serviceCheckLogDao;

    @Test
    public void testInit() {
        assertNotNull(serviceCheckLogDao);
    }


    @Test
    public void insertServiceCheckLog() throws Exception {
        Date date = new Date();
        ServiceCheckLog serviceCheckLog = new ServiceCheckLog(0, 1, "Service 1", date);
        long startId = 7;
        for (int i = 0; i < 10; i++) {
            long id = serviceCheckLogDao.insertServiceCheckLog(serviceCheckLog);
            assertEquals(startId + i, id);
        }
    }

    @Test
    public void getServiceCheckLogById() throws Exception {
        ServiceCheckLog serviceCheckLog = serviceCheckLogDao.getServiceCheckLogById(1);
        assertNotNull(serviceCheckLog);
        assertNotNull(serviceCheckLog.getDate());
        assertEquals(1, serviceCheckLog.getId());
        assertEquals(1, serviceCheckLog.getCheckLogId());
        assertEquals("Service 1", serviceCheckLog.getServiceName());
    }

    @Test
    public void getServiceCheckLogForCheckLog() throws Exception {
        List<ServiceCheckLog> serviceCheckLogs = serviceCheckLogDao.getServiceCheckLogsForCheckLog(1);
        assertNotNull(serviceCheckLogs);
        assertEquals(3, serviceCheckLogs.size());
    }

}