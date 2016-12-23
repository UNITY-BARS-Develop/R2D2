package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.AbstractDaoTest;
import com.unitybars.r2d2.dao.TaskCheckLogDao;
import com.unitybars.r2d2.entity.CheckStatus;
import com.unitybars.r2d2.entity.TaskCheckLog;
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
public class SqliteTaskCheckLogDaoTest extends AbstractDaoTest {
    @Autowired
    private TaskCheckLogDao taskCheckLogDao;

    @Test
    public void testInit() {
        assertNotNull(taskCheckLogDao);
    }

    @Test
    public void insertTaskCheckLog() throws Exception {
        TaskCheckLog taskCheckLog = new TaskCheckLog(1, "Task 1", "JSON", "200",
                "200", new Date(), CheckStatus.ERROR, 1);
        long startId = 7;
        for (int i = 0; i < 10; i++) {
            long id = taskCheckLogDao.insertTaskCheckLog(taskCheckLog);
            assertEquals(startId + i, id);
        }
    }

    @Test
    public void getTaskCheckLogById() throws Exception {
        TaskCheckLog taskCheckLog = taskCheckLogDao.getTaskCheckLogById(1);
        assertNotNull(taskCheckLog);
        assertEquals(1, taskCheckLog.getId());
        assertEquals(CheckStatus.ERROR, taskCheckLog.getCheckStatus());
        assertNotNull(taskCheckLog.getDate());
        assertEquals("200", taskCheckLog.getExpectedValue());
        assertEquals("401", taskCheckLog.getResultValue());
        assertEquals("JSON", taskCheckLog.getTaskType());
        assertEquals("Task 1. S1", taskCheckLog.getTaskName());
    }

    @Test
    public void getTaskCheckLogsForServiceCheckLog() throws Exception {
        List<TaskCheckLog> taskCheckLogs = taskCheckLogDao.getTaskCheckLogsForServiceCheckLog(1);
        assertNotNull(taskCheckLogs);
        assertEquals(2, taskCheckLogs.size());
    }
}