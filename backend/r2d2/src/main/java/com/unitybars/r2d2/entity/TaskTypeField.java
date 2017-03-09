package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public class TaskTypeField {
    private int id;
    private TaskTypeId taskTypeId;
    private String name;
    private int count;
    private String format;

    public TaskTypeField() {
    }

    public TaskTypeField(int id, TaskTypeId taskTypeId, String name, int count, String format) {
        this.id = id;
        this.taskTypeId = taskTypeId;
        this.name = name;
        this.count = count;
        this.format = format;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public TaskTypeId getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(TaskTypeId taskTypeId) {
        this.taskTypeId = taskTypeId;
    }
}
