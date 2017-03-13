package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.ServiceTypeParameterDao;
import com.unitybars.r2d2.entity.ServiceType;
import com.unitybars.r2d2.entity.response.ServiceTypeParameterJson;
import com.unitybars.r2d2.entity.response.ServiceTypeWithParametersJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oleg.nestyuk
 * Date: 07-Mar-17.
 */
@Service
public class ServiceTypeService {

    @Autowired
    private ServiceTypeParameterDao serviceTypeParameterDao;

    public List<ServiceTypeWithParametersJson> getServiceTypesWithParameters() {
        List<ServiceTypeParameterJson> allParameters = serviceTypeParameterDao.getAllServiceTypeParameters();
        Map<ServiceType, ServiceTypeWithParametersJson> serviceTypeMap = new HashMap<>();
        for (ServiceTypeParameterJson parameter : allParameters) {
            ServiceTypeWithParametersJson serviceTypeWithParameters = serviceTypeMap
                    .computeIfAbsent(
                            parameter.getServiceType(),
                            k -> new ServiceTypeWithParametersJson(parameter.getServiceType(), new ArrayList<>())
                    );
            serviceTypeWithParameters.getParameters().add(parameter);
        }
        return new ArrayList<>(serviceTypeMap.values());
    }
}
