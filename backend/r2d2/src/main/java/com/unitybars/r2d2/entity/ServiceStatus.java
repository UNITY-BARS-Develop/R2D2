package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 08-Dec-16.
 */
public enum ServiceStatus {
    ACTIVE, PAUSED, DELETED;

    public static ServiceStatus getServiceType(String serviceStatus) {
        for (ServiceStatus e : values()) {
            if (e.toString().equalsIgnoreCase(serviceStatus)) {
                return e;
            }
        }
        return null;
    }

}
