package com.unitybars.r2d2.service.executor.task;

import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.Task;
import com.unitybars.r2d2.entity.TaskCheckLog;
import com.unitybars.r2d2.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */

@org.springframework.stereotype.Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JSONTaskExecutor implements TaskExecutor {
    @Autowired
    private RequestService requestService;
    private Task task;
    private Service service;
    private TaskCheckLog taskCheckLog;

    @Override
    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public TaskCheckLog doCheck() {
        // TODO add check
        return null;
    }
}
