package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.TaskDao;
import com.unitybars.r2d2.dao.TaskFieldValueDao;
import com.unitybars.r2d2.entity.Task;
import com.unitybars.r2d2.entity.TaskFieldValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TaskFieldValueDao taskFieldValueDao;

    void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    void setTaskFieldValueDao(TaskFieldValueDao taskFieldValueDao) {
        this.taskFieldValueDao = taskFieldValueDao;
    }

    public List<Task> getAllTasksForService(String serviceId) {
        return taskDao.getTasksForService(serviceId);
    }

    public Task getTaskById(int taskId) {
        Task task = taskDao.getTaskById(taskId);
        task.setFields(taskFieldValueDao.getTaskFieldValuesForTask(taskId));
        return task;
    }

    public List<Task> getAllTasksWithFieldsForService(String serviceId) {
        List<Task> taskList = taskDao.getTasksForService(serviceId);
        List<TaskFieldValue> taskFieldValueList = taskFieldValueDao.getAllTaskFieldValues();
        for (Task task : taskList) {
            List<TaskFieldValue> fieldValuesForConcreteTask = taskFieldValueList.stream()
                    .filter(t -> Objects.equals(t.getTaskId(), task.getId()))
                    .collect(toList());
            task.setFields(fieldValuesForConcreteTask);
        }
        return taskList;
    }


}
