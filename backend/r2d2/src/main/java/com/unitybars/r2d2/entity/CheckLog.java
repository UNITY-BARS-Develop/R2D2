package com.unitybars.r2d2.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
public class CheckLog {
    private long id;
    private Date date;
    private List<ServiceCheckLog> serviceCheckLogs;

    public CheckLog() {
    }

    public CheckLog(long id, Date date) {
        this.id = id;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ServiceCheckLog> getServiceCheckLogs() {
        return serviceCheckLogs;
    }

    public void setServiceCheckLogs(List<ServiceCheckLog> serviceCheckLogs) {
        this.serviceCheckLogs = serviceCheckLogs;
    }
}
