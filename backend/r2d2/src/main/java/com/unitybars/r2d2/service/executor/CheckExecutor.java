package com.unitybars.r2d2.service.executor;

import com.unitybars.r2d2.entity.*;
import com.unitybars.r2d2.exception.FunctionalityNotImplemented;
import com.unitybars.r2d2.service.LogService;
import com.unitybars.r2d2.service.ServiceService;
import com.unitybars.r2d2.service.TaskService;
import com.unitybars.r2d2.service.executor.task.StatusCodeTaskExecutorCreator;
import com.unitybars.r2d2.service.executor.task.TaskExecutor;
import com.unitybars.r2d2.service.executor.task.TaskExecutorCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import java.util.Date;
import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
@org.springframework.stereotype.Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CheckExecutor {

    @Autowired
    private LogService logService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ApplicationContext context;

    private Logger logger = LoggerFactory.getLogger(CheckExecutor.class);
    private CheckLog checkLog;
    private List<Service> services;

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public void start() {
        initCheckLog();
        services = serviceService.getAllServicesWithParameters();
        for (Service service : services) {
            startCheckForService(service);
        }
    }

    private void startCheckForService(Service service) {
        ServiceCheckLog serviceCheckLog = initServiceCheckLog(service);
        List<Task> tasks = taskService.getAllTasksWithFieldsForService(service.getId());
        for (Task task : tasks) {
            startCheckForTask(task, service, serviceCheckLog);
        }
    }

    private void startCheckForTask(Task task, Service service, ServiceCheckLog serviceCheckLog) {
        TaskCheckLog taskCheckLog;
        try {
            TaskExecutorCreator taskExecutorCreator = getTaskExecutorCreator(task);
            TaskExecutor taskExecutor = taskExecutorCreator.factoryMethod(task, service);
            taskCheckLog = taskExecutor.doCheck();
            if (taskCheckLog != null) {
                taskCheckLog.setServiceCheckLogId(serviceCheckLog.getId());
            } else {
                throw new NullPointerException("TaskCheckLog can't be null");
            }
        } catch (Exception e) {
            logger.error("Error happened when try to check task", e);
            taskCheckLog = getUnexpectedErrorTaskCheckLog(task, serviceCheckLog);
        }
        writeTaskCheckLog(taskCheckLog);
    }

    private void writeTaskCheckLog(TaskCheckLog taskCheckLog) {
        taskCheckLog.setId(logService.insertTaskCheckLog(taskCheckLog));
        logTaskCheckResult(taskCheckLog);
    }

    private TaskCheckLog getUnexpectedErrorTaskCheckLog(Task task, ServiceCheckLog serviceCheckLog) {
        return new TaskCheckLog(0, task.getName(), task.getTaskType().name(), task.getExpectedValue(), null,
                new Date(), CheckStatus.UNEXPECTED_ERROR, serviceCheckLog.getId());
    }

    private void logTaskCheckResult(TaskCheckLog taskCheckLog) {
        logger.info(taskCheckLog.toString());
    }

    private TaskExecutorCreator getTaskExecutorCreator(Task task) throws FunctionalityNotImplemented {
        switch (task.getTaskType()) {
            case JSON:
                return context.getBean(TaskExecutorCreator.class);
            case StatusCode:
                return context.getBean(StatusCodeTaskExecutorCreator.class);
            case SQLRequest:
                logger.error("Error when try create taskExecuteCreator for SQLRequests. SQLRequest not supported in current version");
                throw new FunctionalityNotImplemented("SQLRequests not supported in current version");
            default:
                throw new NullPointerException("Task type can't be null");
        }
    }

    private void initCheckLog() {
        checkLog = new CheckLog(0, new Date());
        long checkLogId = logService.insertCheckLog(checkLog);
        checkLog.setId(checkLogId);
    }

    private ServiceCheckLog initServiceCheckLog(Service service) {
        ServiceCheckLog serviceCheckLog = new ServiceCheckLog(0, checkLog.getId(), service.getName(), new Date());
        serviceCheckLog.setId(logService.insertServiceCheckLog(serviceCheckLog));
        return serviceCheckLog;
    }
}