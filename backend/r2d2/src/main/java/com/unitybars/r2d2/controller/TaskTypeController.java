package com.unitybars.r2d2.controller;

import com.unitybars.r2d2.entity.ServiceType;
import com.unitybars.r2d2.entity.TaskTypeField;
import com.unitybars.r2d2.entity.TaskTypeId;
import com.unitybars.r2d2.entity.response.TaskTypeJson;
import com.unitybars.r2d2.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 09-Mar-17.
 */

@RestController
@RequestMapping("/api/v1/tasktypes")
public class TaskTypeController {
    @Autowired
    private TaskTypeService taskTypeService;

    @RequestMapping(value = "servicetype/{servicetype}", method = RequestMethod.GET)
    public List<TaskTypeJson> getTaskTypesForServiceType(@PathVariable("servicetype") ServiceType serviceType) {
        return taskTypeService.getTaskTypesForServiceType(serviceType);
    }

    @RequestMapping(value = "{id}/fields", method = RequestMethod.GET)
    public List<TaskTypeField> getTaskTypeFieldsByTaskType(@PathVariable("id") TaskTypeId taskTypeId) {
        return taskTypeService.getAllTaskTypeFieldsByTaskTypeId(taskTypeId);
    }
}
