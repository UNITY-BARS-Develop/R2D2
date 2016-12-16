package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.Task;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public interface TaskDao {
    List<Task> getAllTasks();

    Task getTaskById(int id);

    List<Task> getTasksForService(int serviceId);
}
