package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 08-Dec-16.
 */
public enum ServiceType {
    WEB, SQL;

    public static ServiceType getServiceType(String serviceType){
        for (ServiceType e : values()) {
            if (e.toString().equalsIgnoreCase(serviceType)){
                return e;
            }
        }
        return null;
    }
}
