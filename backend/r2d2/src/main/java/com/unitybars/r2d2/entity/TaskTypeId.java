package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public enum TaskTypeId {
    JSON(ServiceType.WEB),
    StatusCode(ServiceType.WEB),
    SQLRequest(ServiceType.SQL);

    private ServiceType serviceType;

    TaskTypeId(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public static TaskTypeId getTaskType(String taskType) {
        for (TaskTypeId e : values()) {
            if (e.toString().equalsIgnoreCase(taskType)) {
                return e;
            }
        }
        return null;
    }
}
