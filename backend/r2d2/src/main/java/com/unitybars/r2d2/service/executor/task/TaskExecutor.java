package com.unitybars.r2d2.service.executor.task;

import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.Task;
import com.unitybars.r2d2.entity.TaskCheckLog;
import com.unitybars.r2d2.exception.MissedParameterException;
import com.unitybars.r2d2.exception.RequestExecuteError;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
public interface TaskExecutor {
    TaskCheckLog doCheck() throws MissedParameterException, RequestExecuteError, IOException;
    void setTask(@NotNull Task task);
    void setService(@NotNull Service service);
}
