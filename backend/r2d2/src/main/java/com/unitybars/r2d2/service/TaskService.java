package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.TaskDao;
import com.unitybars.r2d2.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public List<Task> getAllTasksForService(int serviceId) {
        return taskDao.getTasksForService(serviceId);
    }
}
