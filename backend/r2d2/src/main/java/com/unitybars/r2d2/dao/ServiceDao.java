package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.Service;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 08-Dec-16.
 */
public interface ServiceDao {
    List<Service> getAllServices();

    Service getServiceById(int id);
}
