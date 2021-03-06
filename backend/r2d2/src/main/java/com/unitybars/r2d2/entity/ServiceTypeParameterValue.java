package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 13-Dec-16.
 */
public class ServiceTypeParameterValue {
    private int id;
    private String serviceId;
    private ServiceTypeParameter serviceTypeParameter;
    private String value;

    public ServiceTypeParameterValue(int id, String serviceId, ServiceTypeParameter serviceTypeParameter, String value) {
        this.id = id;
        this.serviceId = serviceId;
        this.serviceTypeParameter = serviceTypeParameter;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ServiceTypeParameter getServiceTypeParameter() {
        return serviceTypeParameter;
    }

    public void setServiceTypeParameter(ServiceTypeParameter serviceTypeParameter) {
        this.serviceTypeParameter = serviceTypeParameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
