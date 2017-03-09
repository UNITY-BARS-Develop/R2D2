package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.TaskFieldValue;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public interface TaskFieldValueDao {
    List<TaskFieldValue> getAllTaskFieldValues();

    List<TaskFieldValue> getTaskFieldValuesForTask(String taskId);

    void create(List<TaskFieldValue> fields, String taskId);

    void update(List<TaskFieldValue> fields, String id);
}
