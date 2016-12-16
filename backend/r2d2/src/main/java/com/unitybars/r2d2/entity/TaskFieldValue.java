package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public class TaskFieldValue {
    private int id;
    private int taskId;
    private TaskTypeField taskTypeField;
    private String value;

    public TaskFieldValue() {
    }

    public TaskFieldValue(int id, int taskId, TaskTypeField taskTypeField, String value) {
        this.id = id;
        this.taskId = taskId;
        this.taskTypeField = taskTypeField;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public TaskTypeField getTaskTypeField() {
        return taskTypeField;
    }

    public void setTaskTypeField(TaskTypeField taskTypeField) {
        this.taskTypeField = taskTypeField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
