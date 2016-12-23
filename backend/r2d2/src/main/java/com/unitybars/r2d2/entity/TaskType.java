package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public enum TaskType {
    JSON(ServiceType.WEB),
    StatusCode(ServiceType.WEB),
    SQLRequest(ServiceType.SQL);

    private ServiceType serviceType;

    TaskType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public static TaskType getTaskType(String taskType) {
        for (TaskType e : values()) {
            if (e.toString().equalsIgnoreCase(taskType)) {
                return e;
            }
        }
        return null;
    }
}
