package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.ServiceDao;
import com.unitybars.r2d2.entity.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 08-Dec-16.
 */
@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    @Qualifier("databaseData")
    private ServiceDao serviceDao;

    public List<Service> getAllServices() {
        return serviceDao.getAllServices();
    }

    public Service getServiceById(int id) {
        return serviceDao.getServiceById(id);
    }
}
