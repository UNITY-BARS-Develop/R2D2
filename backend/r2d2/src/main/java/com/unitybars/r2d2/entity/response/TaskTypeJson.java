package com.unitybars.r2d2.entity.response;

import com.unitybars.r2d2.entity.ServiceType;
import com.unitybars.r2d2.entity.TaskTypeId;

/**
 * Created by oleg.nestyuk
 * Date: 07-Mar-17.
 */
public class TaskTypeJson {
    private TaskTypeId id;
    private ServiceType serviceType;
    private String description;

    public TaskTypeJson() {
    }

    public TaskTypeJson(TaskTypeId id, ServiceType serviceType, String description) {
        this.id = id;
        this.serviceType = serviceType;
        this.description = description;
    }

    public TaskTypeId getId() {
        return id;
    }

    public void setId(TaskTypeId id) {
        this.id = id;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
