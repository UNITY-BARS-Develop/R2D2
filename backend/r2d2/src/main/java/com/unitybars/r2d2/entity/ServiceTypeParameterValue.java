package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 13-Dec-16.
 */
public class ServiceTypeParameterValue {
    private int id;
    private int serviceId;
    private String serviceTypeParameter;
    private String value;

    public ServiceTypeParameterValue(int id, int serviceId, String serviceTypeParameter, String value) {
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

    public String getServiceTypeParameter() {
        return serviceTypeParameter;
    }

    public void setServiceTypeParameter(String serviceTypeParameter) {
        this.serviceTypeParameter = serviceTypeParameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}