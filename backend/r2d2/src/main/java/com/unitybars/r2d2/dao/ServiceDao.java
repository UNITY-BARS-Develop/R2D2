package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.ServiceStatus;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 08-Dec-16.
 */
public interface ServiceDao {
    List<Service> getAllServices();

    Service getServiceById(String id);

    void create(Service service);

    void setServiceStatus(String id, ServiceStatus status);
}
