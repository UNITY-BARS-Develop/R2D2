package com.unitybars.r2d2.entity;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public class Task {
    private String id;
    private String serviceId;
    private TaskType taskType;
    private String expectedValue;
    private String name;
    private List<TaskFieldValue> fields;

    public Task() {
    }

    public Task(String id, String serviceId, TaskType taskType, String expectedValue, String name) {
        this.id = id;
        this.serviceId = serviceId;
        this.taskType = taskType;
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

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
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
