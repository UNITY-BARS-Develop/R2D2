package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.ServiceDao;
import com.unitybars.r2d2.dao.ServiceTypeParameterDao;
import com.unitybars.r2d2.dao.ServiceTypeParameterValueDao;
import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.ServiceType;
import com.unitybars.r2d2.entity.ServiceTypeParameter;
import com.unitybars.r2d2.entity.ServiceTypeParameterValue;
import com.unitybars.r2d2.exception.InvalidRequestBody;
import org.springframework.beans.factory.annotation.Autowired;

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
                    .filter(p -> p.getServiceId() == service.getId())
                    .collect(toList());
            service.setParametersFromList(concreteServiceParameters);
        }
        return services;
    }

    public List<Service> getAllServices() {
        return serviceDao.getAllServices();
    }

    public Service getServiceById(int id) {
        Service service = serviceDao.getServiceById(id);
        List<ServiceTypeParameterValue> serviceTypeParameterValues =
                serviceTypeParameterValueDao.getServiceTypeParameterValuesForService(service.getId());
        service.setParametersFromList(serviceTypeParameterValues);
        return service;
    }

    public String add(Service service) throws InvalidRequestBody {
        if (validateService(service)) {
            String serviceId = UUID.randomUUID().toString();
            service.setId(serviceId);
            serviceDao.create(service);
            serviceTypeParameterValueDao.create(service.getParameters(), serviceId);
            return serviceId;
        } else {
            throw new InvalidRequestBody();
        }
    }

    private boolean validateService(Service service) {
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


    public List<ServiceTypeParameter> getAllServiceTypeParametersByServiceType(ServiceType serviceType) {
        return serviceTypeParameterDao.getByServiceType(serviceType);
    }
}
