package com.unitybars.r2d2.entity.response;

import com.unitybars.r2d2.entity.ServiceStatus;

/**
 * Created by oleg.nestyuk
 * Date: 07-Mar-17.
 */
public class ServiceStatusJson {
    private ServiceStatus status;

    public ServiceStatusJson() {
    }

    public ServiceStatusJson(ServiceStatus status) {
        this.status = status;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }
}