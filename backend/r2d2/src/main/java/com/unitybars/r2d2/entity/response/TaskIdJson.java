package com.unitybars.r2d2.entity.response;

/**
 * Created by oleg.nestyuk
 * Date: 06-Mar-17.
 */
public class TaskIdJson {
    private String taskId;

    public TaskIdJson() {
    }

    public TaskIdJson(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}