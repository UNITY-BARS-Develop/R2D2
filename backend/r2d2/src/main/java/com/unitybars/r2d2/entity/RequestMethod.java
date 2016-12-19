package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
public enum RequestMethod {
    GET, POST, PUT, DELETE;

    public static RequestMethod getRequestMethod(String requestMethod) {
        for (RequestMethod e : values()) {
            if (e.toString().equalsIgnoreCase(requestMethod)) {
                return e;
            }
        }
        return null;
    }
}
