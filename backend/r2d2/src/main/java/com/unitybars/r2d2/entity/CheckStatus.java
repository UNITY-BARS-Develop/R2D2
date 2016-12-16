package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
public enum CheckStatus {
    PERFORMED, SUCCESS, ERROR, UNEXPECTED_ERROR;

    public static CheckStatus getCheckStatus(String checkStatus) {
        for (CheckStatus e : values()) {
            if (e.toString().equalsIgnoreCase(checkStatus)) {
                return e;
            }
        }
        return null;
    }
}
