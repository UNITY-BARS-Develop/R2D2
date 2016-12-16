package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public class TaskTypeField {
    private int id;
    private TaskType taskType;
    private String name;
    private int count;
    private String format;

    public TaskTypeField() {
    }

    public TaskTypeField(int id, TaskType taskType, String name, int count, String format) {
        this.id = id;
        this.taskType = taskType;
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

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }
}
