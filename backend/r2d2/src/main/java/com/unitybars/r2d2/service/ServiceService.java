package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.ServiceDao;
import com.unitybars.r2d2.dao.ServiceTypeParameterDao;
import com.unitybars.r2d2.dao.ServiceTypeParameterValueDao;
import com.unitybars.r2d2.entity.*;
import com.unitybars.r2d2.exception.InvalidRequestBodyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

/**
 * Created by oleg.nestyuk
 * Date: 08-Dec-16.
 */
@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    private ServiceDao serviceDao;
    @Autowired
    private ServiceTypeParameterValueDao serviceTypeParameterValueDao;
    @Autowired
    private ServiceTypeParameterDao serviceTypeParameterDao;

    void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    void setServiceTypeParameterValueDao(ServiceTypeParameterValueDao serviceTypeParameterValueDao) {
        this.serviceTypeParameterValueDao = serviceTypeParameterValueDao;
    }

    public List<Service> getAllServicesWithParameters() {
        List<Service> services = serviceDao.getAllServices();
        List<ServiceTypeParameterValue> servicesParameters = serviceTypeParameterValueDao.getAllServiceTypeParameterValues();
        for (Service service : services) {
            List<ServiceTypeParameterValue> concreteServiceParameters = servicesParameters.stream()
                    .filter(p -> p.getServiceId().equals(service.getId()))
                    .collect(toList());
            service.setParametersFromList(concreteServiceParameters);
        }
        return services;
    }

    public List<Service> getAllServices() {
        return serviceDao.getAllServices();
    }

    public Service getServiceById(String id) {
        Service service = serviceDao.getServiceById(id);
        List<ServiceTypeParameterValue> serviceTypeParameterValues =
                serviceTypeParameterValueDao.getServiceTypeParameterValuesForService(service.getId());
        service.setParametersFromList(serviceTypeParameterValues);
        return service;
    }

    @Transactional
    public String add(Service service) throws InvalidRequestBodyException {
        if (validateServiceToCreate(service)) {
            String serviceId = UUID.randomUUID().toString();
            service.setId(serviceId);
            serviceDao.create(service);
            serviceTypeParameterValueDao.create(service.getParameters(), serviceId);
            return serviceId;
        } else {
            throw new InvalidRequestBodyException();
        }
    }

    @Transactional
    public String update(Service service) throws InvalidRequestBodyException {
        if (validateServiceToUpdate(service)) {
            serviceTypeParameterValueDao.update(service.getParameters(), service.getId());
            serviceDao.update(service);
            return service.getId();
        } else {
            throw new InvalidRequestBodyException();
        }
    }

    private boolean validateServiceToCreate(Service service) {
        if (service != null && service.getServiceStatus() != null && service.getServiceType() != null) {
            List<ServiceTypeParameter> serviceTypeParameters = getAllServiceTypeParametersByServiceType(service.getServiceType());
            for (ServiceTypeParameter serviceTypeParameter : serviceTypeParameters) {
                if (service.getParameters().get(serviceTypeParameter) == null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean validateServiceToUpdate(Service service) {
        return service != null && service.getServiceStatus() != null && service.getId() != null
                && service.getId().length() > 0;
    }

    public List<ServiceTypeParameter> getAllServiceTypeParametersByServiceType(ServiceType serviceType) {
        return serviceTypeParameterDao.getByServiceType(serviceType);
    }

    public void setServiceStatus(String id, ServiceStatus status) {
        serviceDao.setServiceStatus(id, status);
    }
}