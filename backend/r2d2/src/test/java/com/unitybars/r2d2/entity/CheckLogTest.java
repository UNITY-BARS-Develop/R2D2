package com.unitybars.r2d2.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oleg.nestyuk
 * Date: 21-Dec-16.
 */
public class CheckLogTest {

    @Test
    public void getStatusSuccess() throws Exception {
        CheckLog checkLog = new CheckLog();
        checkLog.setServiceCheckLogs(getSuccessListServiceCheckLog());
        assertEquals(CheckStatus.SUCCESS, checkLog.getStatus());
    }

    @Test
    public void getStatusError() throws Exception {
        CheckLog checkLog = new CheckLog();
        checkLog.setServiceCheckLogs(getErrorListServiceCheckLog());
        assertEquals(CheckStatus.ERROR, checkLog.getStatus());
    }

    @Test
    public void getStatusUnexpectedError() throws Exception {
        CheckLog checkLog = new CheckLog();
        checkLog.setServiceCheckLogs(getUnexpectedErrorListServiceCheckLog());
        assertEquals(CheckStatus.ERROR, checkLog.getStatus());
    }

    private List<ServiceCheckLog> getSuccessListServiceCheckLog() {
        ServiceCheckLog serviceCheckLog1 = new ServiceCheckLog(1, 1, "Service", new Date());
        ServiceCheckLog serviceCheckLog2 = new ServiceCheckLog(2, 1, "Service", new Date());
        serviceCheckLog1.setTaskCheckLogs(getSuccessListTaskCheckLog());
        serviceCheckLog2.setTaskCheckLogs(getSuccessListTaskCheckLog());
        List<ServiceCheckLog> serviceCheckLogs = new ArrayList<>();
        serviceCheckLogs.add(serviceCheckLog1);
        serviceCheckLogs.add(serviceCheckLog2);
        return serviceCheckLogs;
    }

    private List<ServiceCheckLog> getErrorListServiceCheckLog() {
        ServiceCheckLog serviceCheckLog1 = new ServiceCheckLog(1, 1, "Service", new Date());
        ServiceCheckLog serviceCheckLog2 = new ServiceCheckLog(2, 1, "Service", new Date());
        serviceCheckLog1.setTaskCheckLogs(getSuccessListTaskCheckLog());
        serviceCheckLog2.setTaskCheckLogs(geErrorsListTaskCheckLog());
        List<ServiceCheckLog> serviceCheckLogs = new ArrayList<>();
        serviceCheckLogs.add(serviceCheckLog1);
        serviceCheckLogs.add(serviceCheckLog2);
        return serviceCheckLogs;
    }

    private List<ServiceCheckLog> getUnexpectedErrorListServiceCheckLog() {
        ServiceCheckLog serviceCheckLog1 = new ServiceCheckLog(1, 1, "Service", new Date());
        ServiceCheckLog serviceCheckLog2 = new ServiceCheckLog(2, 1, "Service", new Date());
        serviceCheckLog1.setTaskCheckLogs(geUnexpectedErrorsListTaskCheckLog());
        serviceCheckLog2.setTaskCheckLogs(getSuccessListTaskCheckLog());
        List<ServiceCheckLog> serviceCheckLogs = new ArrayList<>();
        serviceCheckLogs.add(serviceCheckLog1);
        serviceCheckLogs.add(serviceCheckLog2);
        return serviceCheckLogs;
    }

    private List<TaskCheckLog> getSuccessListTaskCheckLog() {
        List<TaskCheckLog> taskCheckLogs = new ArrayList<>();
        taskCheckLogs.add(new TaskCheckLog(1, "Task", "WEB", "1", "1",
                new Date(), CheckStatus.SUCCESS, 1, null));
        taskCheckLogs.add(new TaskCheckLog(2, "Task", "WEB", "1", "1",
                new Date(), CheckStatus.SUCCESS, 1, null));
        taskCheckLogs.add(new TaskCheckLog(3, "Task", "WEB", "1", "1",
                new Date(), CheckStatus.SUCCESS, 1, null));
        return taskCheckLogs;
    }

    private List<TaskCheckLog> geErrorsListTaskCheckLog() {
        List<TaskCheckLog> taskCheckLogs = new ArrayList<>();
        taskCheckLogs.add(new TaskCheckLog(1, "Task", "WEB", "1", "1",
                new Date(), CheckStatus.SUCCESS, 1, null));
        taskCheckLogs.add(new TaskCheckLog(2, "Task", "WEB", "1", "2",
                new Date(), CheckStatus.ERROR, 1, null));
        taskCheckLogs.add(new TaskCheckLog(3, "Task", "WEB", "1", "1",
                new Date(), CheckStatus.SUCCESS, 1, null));
        return taskCheckLogs;
    }

    private List<TaskCheckLog> geUnexpectedErrorsListTaskCheckLog() {
        List<TaskCheckLog> taskCheckLogs = new ArrayList<>();
        taskCheckLogs.add(new TaskCheckLog(1, "Task", "WEB", "1", "1",
                new Date(), CheckStatus.SUCCESS, 1, null));
        taskCheckLogs.add(new TaskCheckLog(2, "Task", "WEB", "1", null,
                new Date(), CheckStatus.UNEXPECTED_ERROR, 1, null));
        taskCheckLogs.add(new TaskCheckLog(3, "Task", "WEB", "1", "1",
                new Date(), CheckStatus.SUCCESS, 1, null));
        return taskCheckLogs;
    }

}