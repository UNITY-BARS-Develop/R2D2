package com.unitybars.r2d2.service.executor.task;

import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
@org.springframework.stereotype.Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StatusCodeTaskExecutorCreator implements TaskExecutorCreator {

    @Autowired
    private ApplicationContext context;

    @Override
    public TaskExecutor factoryMethod(Task task, Service service) {
        TaskExecutor taskExecutor = context.getBean(StatusCodeTaskExecutor.class);
        taskExecutor.setTask(task);
        taskExecutor.setService(service);
        return taskExecutor;
    }
}
