package com.unitybars.r2d2.service;

import com.unitybars.r2d2.AbstractTest;
import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.entity.CheckStatus;
import com.unitybars.r2d2.entity.ServiceCheckLog;
import com.unitybars.r2d2.entity.TaskCheckLog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oleg.nestyuk
 * Date: 20-Dec-16.
 */
public class CheckServiceTest extends AbstractTest{

    @Autowired
    private CheckService checkService;

    @Test
    public void startCheck() throws Exception {
        // TODO
    }

    @Test
    public void sendCheckResult() throws Exception {
        CheckLog checkLog = getCheckLog();
        checkService.sendCheckResult(checkLog);
    }

    private CheckLog getCheckLog() {
        List<TaskCheckLog> taskCheckLogList = new ArrayList<>();
        taskCheckLogList.add(new TaskCheckLog(1, "Task 1", "WEB", "200",
                "200", new Date(), CheckStatus.SUCCESS, 1));
        taskCheckLogList.add(new TaskCheckLog(2, "Task 2", "WEB", "200",
                "404", new Date(), CheckStatus.ERROR, 1));
        ServiceCheckLog serviceCheckLog1 = new ServiceCheckLog(1, 1, "Service 1", new Date());
        serviceCheckLog1.setTaskCheckLogs(taskCheckLogList);
        ServiceCheckLog serviceCheckLog2 = new ServiceCheckLog(2, 1, "Service 2", new Date());
        serviceCheckLog2.setTaskCheckLogs(taskCheckLogList);
        List<ServiceCheckLog> serviceCheckLogs = new ArrayList<>();
        serviceCheckLogs.add(serviceCheckLog1);
        serviceCheckLogs.add(serviceCheckLog2);
        CheckLog checkLog = new CheckLog(1, new Date());
        checkLog.setServiceCheckLogs(serviceCheckLogs);
        return checkLog;
    }
}