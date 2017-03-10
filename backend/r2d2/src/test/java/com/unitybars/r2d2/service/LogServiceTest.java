package com.unitybars.r2d2.service;

import com.unitybars.r2d2.AbstractTest;
import com.unitybars.r2d2.dao.*;
import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.entity.CheckStatus;
import com.unitybars.r2d2.entity.ServiceCheckLog;
import com.unitybars.r2d2.entity.TaskCheckLog;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
public class LogServiceTest extends AbstractTest {

    @Autowired
    private LogService logService;

    @Mock
    private CheckLogDao checkLogDao;
    @Mock
    private ServiceCheckLogDao serviceCheckLogDao;
    @Mock
    private TaskCheckLogDao taskCheckLogDao;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        logService.setCheckLogDao(checkLogDao);
        logService.setServiceCheckLog(serviceCheckLogDao);
        logService.setTaskCheckLogDao(taskCheckLogDao);
    }

    @Test
    public void testInit() {
        assertNotNull(logService);
        assertNotNull(checkLogDao);
        assertNotNull(serviceCheckLogDao);
        assertNotNull(taskCheckLogDao);
    }

    @Test
    public void insertCheckLog() throws Exception {
        CheckLog checkLog = new CheckLog(0, new Date());
        when(checkLogDao.insertCheckLog(checkLog)).thenReturn(1L);
        assertEquals(1, logService.insertCheckLog(checkLog));
    }

    @Test
    public void insertServiceCheckLog() throws Exception {
        ServiceCheckLog serviceCheckLog = new ServiceCheckLog(0, 1, "Service 1", new Date());
        when(serviceCheckLogDao.insertServiceCheckLog(serviceCheckLog)).thenReturn(1L);
        assertEquals(1, logService.insertServiceCheckLog(serviceCheckLog));
    }

    @Test
    public void insertTaskCheckLog() throws Exception {
        TaskCheckLog taskCheckLog = new TaskCheckLog(1, "Task 1", "JSON", "200",
                "200", new Date(), CheckStatus.ERROR, 1, null);
        when(taskCheckLogDao.insertTaskCheckLog(taskCheckLog)).thenReturn(1L);
        assertEquals(1, logService.insertTaskCheckLog(taskCheckLog));
    }

    @Test
    public void getAllCheckLogs() throws Exception {
        List<CheckLog> checkLogList = new ArrayList<>();
        checkLogList.add(new CheckLog(1, new Date()));
        checkLogList.add(new CheckLog(2, new Date()));
        checkLogList.add(new CheckLog(3, new Date()));
        checkLogList.add(new CheckLog(4, new Date()));
        when(checkLogDao.getAllCheckLogs()).thenReturn(checkLogList);
        assertEquals(4, logService.getAllCheckLogs().size());
    }

    @Test
    public void getCheckLogById() throws Exception {
        CheckLog checkLogItem = new CheckLog(1, new Date());
        List<ServiceCheckLog> serviceCheckLogs1 = new ArrayList<>();
        serviceCheckLogs1.add(new ServiceCheckLog(1, 1, "Service 1", new Date()));
        serviceCheckLogs1.add(new ServiceCheckLog(2, 1, "Service 2", new Date()));
        List<TaskCheckLog> taskCheckLogs1 = new ArrayList<>();
        List<TaskCheckLog> taskCheckLogs2 = new ArrayList<>();
        taskCheckLogs1.add(new TaskCheckLog(1, "Task 1. S1", "JSON", "401",
                "200", new Date(), CheckStatus.ERROR, 1, null));
        taskCheckLogs2.add(new TaskCheckLog(2, "Task 1. S2", "JSON", "200",
                "200", new Date(), CheckStatus.SUCCESS, 2, null));

        when(taskCheckLogDao.getTaskCheckLogsForServiceCheckLog(1)).thenReturn(taskCheckLogs1);
        when(taskCheckLogDao.getTaskCheckLogsForServiceCheckLog(2)).thenReturn(taskCheckLogs2);
        when(serviceCheckLogDao.getServiceCheckLogsForCheckLog(1)).thenReturn(serviceCheckLogs1);
        when(checkLogDao.getCheckLogById(1)).thenReturn(checkLogItem);

        CheckLog checkLog = logService.getCheckLogById(1);
        assertNotNull(checkLog);
        assertNotNull(checkLog.getServiceCheckLogs());
        assertEquals(2, checkLog.getServiceCheckLogs().size());
        assertEquals("Service 1", checkLog.getServiceCheckLogs().get(0).getServiceName());
        assertEquals("Service 2", checkLog.getServiceCheckLogs().get(1).getServiceName());
        assertNotNull(checkLog.getServiceCheckLogs().get(0).getTaskCheckLogs());
        assertEquals(1, checkLog.getServiceCheckLogs().get(0).getTaskCheckLogs().size());
        assertEquals("Task 1. S1", checkLog.getServiceCheckLogs().get(0).getTaskCheckLogs().get(0).getTaskName());
        assertEquals("Task 1. S2", checkLog.getServiceCheckLogs().get(1).getTaskCheckLogs().get(0).getTaskName());
    }

}