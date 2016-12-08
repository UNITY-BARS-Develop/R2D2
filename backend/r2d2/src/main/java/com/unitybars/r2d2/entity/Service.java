package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 08-Dec-16.
 */
public class Service {
    private int id;
    private String name;
    private ServiceType serviceType;
    private ServiceStatus serviceStatus;

    public Service(int id, String name, ServiceType serviceType, ServiceStatus serviceStatus) {
        this.id = id;
        this.name = name;
        this.serviceType = serviceType;
        this.serviceStatus = serviceStatus;
    }

    public Service() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
