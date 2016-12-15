package com.unitybars.r2d2.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
public class ServiceCheckLog {
    private long id;
    private long checkLogId;
    private String serviceName;
    private Date date;
    private List<TaskCheckLog> taskCheckLogs;

    public ServiceCheckLog() {
    }

    public ServiceCheckLog(long id, long checkLogId, String serviceName, Date date) {
        this.id = id;
        this.checkLogId = checkLogId;
        this.serviceName = serviceName;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCheckLogId() {
        return checkLogId;
    }

    public void setCheckLogId(long checkLogId) {
        this.checkLogId = checkLogId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<TaskCheckLog> getTaskCheckLogs() {
        return taskCheckLogs;
    }

    public void setTaskCheckLogs(List<TaskCheckLog> taskCheckLogs) {
        this.taskCheckLogs = taskCheckLogs;
    }
}
