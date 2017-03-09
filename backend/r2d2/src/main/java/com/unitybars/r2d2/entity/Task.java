package com.unitybars.r2d2.entity;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public class Task {
    private String id;
    private String serviceId;
    private TaskTypeId taskTypeId;
    private String expectedValue;
    private String name;
    private List<TaskFieldValue> fields;

    public Task() {
    }

    public Task(String id, String serviceId, TaskTypeId taskTypeId, String expectedValue, String name) {
        this.id = id;
        this.serviceId = serviceId;
        this.taskTypeId = taskTypeId;
        this.expectedValue = expectedValue;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public TaskTypeId getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(TaskTypeId taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskFieldValue> getFields() {
        return fields;
    }

    public void setFields(List<TaskFieldValue> fields) {
        this.fields = fields;
    }
}
