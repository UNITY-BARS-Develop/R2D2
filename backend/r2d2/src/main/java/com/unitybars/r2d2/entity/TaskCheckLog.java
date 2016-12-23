package com.unitybars.r2d2.entity;

import java.util.Date;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
public class TaskCheckLog {
    private long id;
    private String taskName;
    private String taskType;
    private String expectedValue;
    private String resultValue;
    private Date date;
    private CheckStatus checkStatus;
    private long serviceCheckLogId;

    public TaskCheckLog() {
    }

    public TaskCheckLog(long id, String taskName, String taskType, String expectedValue, String resultValue, Date date,
                        CheckStatus checkStatus, long serviceCheckLogId) {
        this.id = id;
        this.taskName = taskName;
        this.taskType = taskType;
        this.expectedValue = expectedValue;
        this.resultValue = resultValue;
        this.date = date;
        this.checkStatus = checkStatus;
        this.serviceCheckLogId = serviceCheckLogId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    public long getServiceCheckLogId() {
        return serviceCheckLogId;
    }

    public void setServiceCheckLogId(long serviceCheckLogId) {
        this.serviceCheckLogId = serviceCheckLogId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Task: %s -> %s\n\tExpected: %s\n\tResult: %s", taskName, checkStatus.name(),
                expectedValue, resultValue);
    }
}
