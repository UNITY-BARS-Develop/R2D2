package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.ServiceTypeParameter;
import com.unitybars.r2d2.entity.ServiceTypeParameterValue;

import java.util.List;
import java.util.Map;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public interface ServiceTypeParameterValueDao {
    List<ServiceTypeParameterValue> getAllServiceTypeParameterValues();

    List<ServiceTypeParameterValue> getServiceTypeParameterValuesForService(String serviceId);

    void create(Map<ServiceTypeParameter, String> parameters, String serviceId);

    void update(Map<ServiceTypeParameter, String> parameters, String serviceId);
}
