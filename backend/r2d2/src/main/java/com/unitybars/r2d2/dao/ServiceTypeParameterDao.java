package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.ServiceType;
import com.unitybars.r2d2.entity.ServiceTypeParameter;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 06-Mar-17.
 */
public interface ServiceTypeParameterDao {

    List<ServiceTypeParameter> getByServiceType(ServiceType serviceType);
}
