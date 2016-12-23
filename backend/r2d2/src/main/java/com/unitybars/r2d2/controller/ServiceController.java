package com.unitybars.r2d2.controller;

import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 08-Dec-16.
 */
@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Service getServiceById(@PathVariable("id") int id) {
        return serviceService.getServiceById(id);
    }
}