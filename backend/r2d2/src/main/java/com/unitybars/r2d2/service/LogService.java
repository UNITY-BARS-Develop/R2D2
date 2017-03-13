package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.CheckLogDao;
import com.unitybars.r2d2.dao.ServiceCheckLogDao;
import com.unitybars.r2d2.dao.TaskCheckLogDao;
import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.entity.ServiceCheckLog;
import com.unitybars.r2d2.entity.TaskCheckLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
@Service
public class LogService {
    @Autowired
    private CheckLogDao checkLogDao;
    @Autowired
    private ServiceCheckLogDao serviceCheckLogDao;
    @Autowired
    private TaskCheckLogDao taskCheckLogDao;

    public void setCheckLogDao(CheckLogDao checkLogDao) {
        this.checkLogDao = checkLogDao;
    }

    public void setServiceCheckLog(ServiceCheckLogDao serviceCheckLogDao) {
        this.serviceCheckLogDao = serviceCheckLogDao;
    }

    public void setTaskCheckLogDao(TaskCheckLogDao taskCheckLogDao) {
        this.taskCheckLogDao = taskCheckLogDao;
    }

    public long addCheckLog(@NotNull CheckLog checkLog) {
        return checkLogDao.insertCheckLog(checkLog);
    }

    public long addServiceCheckLog(@NotNull ServiceCheckLog serviceCheckLog) {
        return serviceCheckLogDao.insertServiceCheckLog(serviceCheckLog);
    }

    public long addTaskCheckLog(@NotNull TaskCheckLog taskCheckLog) {
        return taskCheckLogDao.insertTaskCheckLog(taskCheckLog);
    }

    public List<CheckLog> getAllCheckLogs() {
        try {
            return checkLogDao.getAllCheckLogs();
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
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