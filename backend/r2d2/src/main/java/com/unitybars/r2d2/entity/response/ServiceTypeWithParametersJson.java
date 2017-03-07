package com.unitybars.r2d2.entity.response;

import com.unitybars.r2d2.entity.ServiceType;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 07-Mar-17.
 */
public class ServiceTypeWithParametersJson {
    private ServiceType serviceType;
    private List<ServiceTypeParameterJson> parameters;

    public ServiceTypeWithParametersJson() {
    }

    public ServiceTypeWithParametersJson(ServiceType serviceType, List<ServiceTypeParameterJson> parameters) {
        this.serviceType = serviceType;
        this.parameters = parameters;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public List<ServiceTypeParameterJson> getParameters() {
        return parameters;
    }

    public void setParameters(List<ServiceTypeParameterJson> parameters) {
        this.parameters = parameters;
    }
}
