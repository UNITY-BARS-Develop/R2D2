package com.unitybars.r2d2.controller;

import com.unitybars.r2d2.entity.response.ServiceTypeWithParametersJson;
import com.unitybars.r2d2.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 07-Mar-17.
 */
@RestController
@RequestMapping("/api/v1/servicetypes")
public class ServiceTypesController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ServiceTypeWithParametersJson> getAllServiceTypes() {
        return serviceTypeService.getServiceTypesWithParameters();
    }

}
