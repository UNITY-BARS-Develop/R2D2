package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.CheckLogDao;
import com.unitybars.r2d2.dao.ServiceCheckLogDao;
import com.unitybars.r2d2.dao.TaskCheckLogDao;
import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.entity.ServiceCheckLog;
import com.unitybars.r2d2.entity.TaskCheckLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
@Service
public class LogService {

    @Autowired
    CheckLogDao checkLogDao;

    @Autowired
    ServiceCheckLogDao serviceCheckLogDao;

    @Autowired
    TaskCheckLogDao taskCheckLogDao;

    public void setCheckLogDao(CheckLogDao checkLogDao) {
        this.checkLogDao = checkLogDao;
    }

    public void setServiceCheckLog(ServiceCheckLogDao serviceCheckLogDao) {
        this.serviceCheckLogDao = serviceCheckLogDao;
    }

    public void setTaskCheckLogDao(TaskCheckLogDao taskCheckLogDao) {
        this.taskCheckLogDao = taskCheckLogDao;
    }

    public long insertCheckLog(@NotNull CheckLog checkLog) {
        return checkLogDao.insertCheckLog(checkLog);
    }

    public long insertServiceCheckLog(@NotNull ServiceCheckLog serviceCheckLog) {
        return serviceCheckLogDao.insertServiceCheckLog(serviceCheckLog);
    }

    public long insertTaskCheckLog(@NotNull TaskCheckLog taskCheckLog) {
        return taskCheckLogDao.insertTaskCheckLog(taskCheckLog);
    }

    public List<CheckLog> getAllCheckLogs() {
        return checkLogDao.getAllCheckLogs();
    }

    public CheckLog getCheckLogById(long checkLogId) {
        CheckLog checkLog = checkLogDao.getCheckLogById(checkLogId);
        List<ServiceCheckLog> serviceCheckLogs = serviceCheckLogDao.getServiceCheckLogsForCheckLog(checkLogId);
        checkLog.setServiceCheckLogs(serviceCheckLogs);
        for (ServiceCheckLog serviceCheckLog : serviceCheckLogs) {
            serviceCheckLog.setTaskCheckLogs(taskCheckLogDao.getTaskCheckLogsForServiceCheckLog(serviceCheckLog.getId()));
        }
        return checkLog;
    }
}