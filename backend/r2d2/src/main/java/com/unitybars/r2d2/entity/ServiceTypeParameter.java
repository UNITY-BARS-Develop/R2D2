package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 13-Dec-16.
 */
public enum ServiceTypeParameter {
    URL, CONNECTION_STRING;

    public static ServiceTypeParameter getServiceTypeParameter(String serviceTypeParameter) {
        for (ServiceTypeParameter e : values()) {
            if (e.toString().equalsIgnoreCase(serviceTypeParameter)) {
                return e;
            }
        }
        return null;
    }
}