package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.ServiceType;
import com.unitybars.r2d2.entity.ServiceTypeParameter;
import com.unitybars.r2d2.entity.response.ServiceTypeParameterJson;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 06-Mar-17.
 */
public interface ServiceTypeParameterDao {

    List<ServiceTypeParameter> getByServiceType(ServiceType serviceType);

    List<ServiceTypeParameterJson> getAllServiceTypeParameters();
}
