package com.unitybars.r2d2.entity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public class Task {
    private int id;
    private int serviceId;
    private TaskType taskType;
    private String expectedValue;
    private String name;
    private List<TaskFieldValue> fields;

    public Task() {
    }

    public Task(int id, int serviceId, TaskType taskType, String expectedValue, String name) {
        this.id = id;
        this.serviceId = serviceId;
        this.taskType = taskType;
        this.expectedValue = expectedValue;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
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
