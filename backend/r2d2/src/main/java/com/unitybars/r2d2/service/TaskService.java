package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.TaskDao;
import com.unitybars.r2d2.dao.TaskFieldValueDao;
import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.Task;
import com.unitybars.r2d2.entity.TaskFieldValue;
import com.unitybars.r2d2.entity.TaskTypeField;
import com.unitybars.r2d2.entity.response.TaskTypeJson;
import com.unitybars.r2d2.exception.InvalidRequestBodyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
@org.springframework.stereotype.Service
public class TaskService {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private TaskTypeService taskTypeService;
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

    public Task getTaskById(String taskId) {
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

    @Transactional
    public String add(Task task) throws InvalidRequestBodyException {
        if (validateTaskToCreate(task)) {
            String taskId = UUID.randomUUID().toString();
            task.setId(taskId);
            taskDao.create(task);
            taskFieldValueDao.create(task.getFields(), taskId);
            return taskId;
        } else {
            throw new InvalidRequestBodyException();
        }
    }

    @Transactional
    public String updateTask(Task task) throws InvalidRequestBodyException {
        if (validateTaskToUpdate(task)) {
            taskDao.update(task);
            taskFieldValueDao.update(task.getFields(), task.getId());
            return task.getId();
        } else {
            throw new InvalidRequestBodyException();
        }
    }

    public boolean validateTaskToCreate(Task task) {
        try {
            if (task.getServiceId() != null && task.getName() != null && task.getName().length() > 0
                    && task.getTaskTypeId() != null && task.getExpectedValue() != null) {
                Service service = serviceService.getServiceById(task.getServiceId());
                if (service != null) {
                    TaskTypeJson taskTypeJson = taskTypeService.getTaskType(task.getTaskTypeId());
                    if (taskTypeJson.getServiceType() == service.getServiceType()) {
                        return isAllTaskFieldsFilledForTask(task);
                    }
                }
                return false;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validateTaskToUpdate(Task task) {
        return task.getId() != null && task.getName() != null && task.getName().length() > 0
                && task.getExpectedValue() != null;
    }

    private boolean isAllTaskFieldsFilledForTask(Task task) {
        Map<Integer, Long> sentTaskFieldTypesCount = task.getFields().stream().collect(
                Collectors.groupingBy(t -> t.getTaskTypeField().getId(), Collectors.counting())
        );
        List<TaskTypeField> taskTypeFieldList = taskTypeService.getAllTaskTypeFieldsByTaskTypeId(task.getTaskTypeId());
        for (TaskTypeField taskTypeField : taskTypeFieldList) {
            int id = taskTypeField.getId();
            long expectCount = taskTypeField.getCount();
            if (expectCount == 0) {
                sentTaskFieldTypesCount.remove(id);
            } else {
                Long sentCount = sentTaskFieldTypesCount.get(id);
                if (sentCount != null && sentCount.equals(expectCount)) {
                    sentTaskFieldTypesCount.remove(id);
                } else {
                    return false;
                }
            }
        }
        return sentTaskFieldTypesCount.size() == 0;
    }

    public void deleteTaskById(String taskId) {
        taskDao.delete(taskId);
    }
}