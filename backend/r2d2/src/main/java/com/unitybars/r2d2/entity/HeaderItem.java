package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
public class HeaderItem {
    private String key;
    private String value;

    public HeaderItem(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
