package com.unitybars.r2d2.entity.response;

/**
 * Created by oleg.nestyuk
 * Date: 06-Mar-17.
 */
public class ServiceId {
    private String serviceId;

    public ServiceId() {
    }

    public ServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
