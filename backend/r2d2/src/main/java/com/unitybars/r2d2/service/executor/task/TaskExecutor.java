package com.unitybars.r2d2.service.executor.task;

import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.Task;
import com.unitybars.r2d2.entity.TaskCheckLog;
import com.unitybars.r2d2.exception.MissedServiceParameterException;
import com.unitybars.r2d2.exception.RequestExecuteError;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
public interface TaskExecutor {
    TaskCheckLog doCheck() throws MissedServiceParameterException, RequestExecuteError;
    void setTask(Task task);
    void setService(Service service);
}
