package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.ServiceTypeParameterValue;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public interface ServiceTypeParameterValueDao {
    List<ServiceTypeParameterValue> getAllServiceTypeParameterValues();

    List<ServiceTypeParameterValue> getServiceTypeParameterValuesForService(int serviceId);
}
