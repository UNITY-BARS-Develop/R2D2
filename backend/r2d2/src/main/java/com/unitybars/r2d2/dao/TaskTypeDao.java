package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.ServiceType;
import com.unitybars.r2d2.entity.TaskTypeId;
import com.unitybars.r2d2.entity.response.TaskTypeJson;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 07-Mar-17.
 */
public interface TaskTypeDao {
    TaskTypeJson getTaskTypeById(TaskTypeId taskTypeId);

    List<TaskTypeJson> getTaskTypesForServiceType(ServiceType serviceType);
}
