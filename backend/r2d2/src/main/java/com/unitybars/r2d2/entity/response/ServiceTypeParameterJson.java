package com.unitybars.r2d2.entity.response;

import com.unitybars.r2d2.entity.ServiceType;
import com.unitybars.r2d2.entity.ServiceTypeParameter;

/**
 * Created by oleg.nestyuk
 * Date: 07-Mar-17.
 */
public class ServiceTypeParameterJson {
    private ServiceType serviceType;
    private ServiceTypeParameter serviceTypeParameter;
    private String name;

    public ServiceTypeParameterJson() {
    }

    public ServiceTypeParameterJson(ServiceType serviceType, ServiceTypeParameter serviceTypeParameter, String name) {
        this.serviceType = serviceType;
        this.serviceTypeParameter = serviceTypeParameter;
        this.name = name;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public ServiceTypeParameter getServiceTypeParameter() {
        return serviceTypeParameter;
    }

    public void setServiceTypeParameter(ServiceTypeParameter serviceTypeParameter) {
        this.serviceTypeParameter = serviceTypeParameter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
