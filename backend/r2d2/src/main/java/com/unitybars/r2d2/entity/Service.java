package com.unitybars.r2d2.entity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 08-Dec-16.
 */
public class Service {
    private int id;
    private String name;
    private ServiceType serviceType;
    private ServiceStatus serviceStatus;
    private HashMap<ServiceTypeParameter, String> parameters;

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

    public HashMap<ServiceTypeParameter, String> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<ServiceTypeParameter, String> parameters) {
        this.parameters = parameters;
    }

    public void setParameters(List<ServiceTypeParameterValue> parameters) {
        this.parameters = convertServiceParameters(parameters);
    }

    protected HashMap<ServiceTypeParameter, String> convertServiceParameters(
            List<ServiceTypeParameterValue> serviceTypeParameterValues) {
        if (serviceTypeParameterValues != null && serviceTypeParameterValues.size() > 0) {
            HashMap<ServiceTypeParameter, String> parameters = new HashMap<>();
            for (ServiceTypeParameterValue parameter : serviceTypeParameterValues) {
                parameters.put(parameter.getServiceTypeParameter(), parameter.getValue());
            }
            return parameters;
        }
        return null;
    }
}
