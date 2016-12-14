package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.ServiceDao;
import com.unitybars.r2d2.dao.ServiceTypeParameterValueDao;
import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.ServiceTypeParameter;
import com.unitybars.r2d2.entity.ServiceTypeParameterValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;

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
            service.setParameters(convertServiceParameters(concreteServiceParameters));
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
        service.setParameters(convertServiceParameters(serviceTypeParameterValues));
        return service;
    }

    private HashMap<String, String> convertServiceParameters(List<ServiceTypeParameterValue> serviceTypeParameterValues) {
        if (serviceTypeParameterValues != null && serviceTypeParameterValues.size() > 0) {
            HashMap<String, String> parameters = new HashMap<>();
            for (ServiceTypeParameterValue parameter : serviceTypeParameterValues) {
                parameters.put(parameter.getServiceTypeParameter(), parameter.getValue());
            }
            return parameters;
        }
        return null;
    }
}
