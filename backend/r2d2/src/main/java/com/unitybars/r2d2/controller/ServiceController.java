package com.unitybars.r2d2.controller;

import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.response.ServiceId;
import com.unitybars.r2d2.exception.InvalidRequestBody;
import com.unitybars.r2d2.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceId add(@RequestBody Service service) throws InvalidRequestBody {
        return new ServiceId(serviceService.add(service));
    }
}