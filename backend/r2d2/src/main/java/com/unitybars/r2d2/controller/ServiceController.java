package com.unitybars.r2d2.controller;

import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.Task;
import com.unitybars.r2d2.entity.response.ServiceIdJson;
import com.unitybars.r2d2.entity.response.ServiceStatusJson;
import com.unitybars.r2d2.exception.InvalidRequestBodyException;
import com.unitybars.r2d2.service.ServiceService;
import com.unitybars.r2d2.service.TaskService;
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
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceIdJson add(@RequestBody Service service) throws InvalidRequestBodyException {
        return new ServiceIdJson(serviceService.addService(service));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceIdJson update(@RequestBody Service service) throws InvalidRequestBodyException {
        return new ServiceIdJson(serviceService.updateService(service));
    }

    @RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
    public void setServiceStatus(@PathVariable("id") String id, @RequestBody ServiceStatusJson serviceStatusJson) {
        serviceService.setServiceStatus(id, serviceStatusJson.getStatus());
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Service getServiceById(@PathVariable("id") String id) {
        return serviceService.getServiceById(id);
    }

    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.GET)
    public List<Task> getTasksForService(@PathVariable("id") String serviceId) {
        return taskService.getAllTasksForService(serviceId);
    }
}