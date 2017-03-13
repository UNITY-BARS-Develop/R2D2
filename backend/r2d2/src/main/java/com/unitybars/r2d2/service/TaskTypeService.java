package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.TaskTypeDao;
import com.unitybars.r2d2.dao.TaskTypeFieldDao;
import com.unitybars.r2d2.entity.ServiceType;
import com.unitybars.r2d2.entity.TaskTypeField;
import com.unitybars.r2d2.entity.TaskTypeId;
import com.unitybars.r2d2.entity.response.TaskTypeJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 07-Mar-17.
 */
@Service
public class TaskTypeService {
    @Autowired
    private TaskTypeDao taskTypeDao;
    @Autowired
    private TaskTypeFieldDao taskTypeFieldDao;

    public TaskTypeJson getTaskType(TaskTypeId taskTypeId) {
        return taskTypeDao.getTaskTypeById(taskTypeId);
    }

    public List<TaskTypeField> getAllTaskTypeFieldsByTaskTypeId(TaskTypeId taskTypeId) {
        return taskTypeFieldDao.getAllTaskTypeFieldsByTaskTypeId(taskTypeId);
    }

    public List<TaskTypeJson> getTaskTypesForServiceType(ServiceType serviceType) {
        return taskTypeDao.getTaskTypesForServiceType(serviceType);
    }
}