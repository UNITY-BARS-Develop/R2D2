package com.unitybars.r2d2.service.executor.task;

import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.Task;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
public interface TaskExecutorCreator {
    TaskExecutor factoryMethod(Task task, Service service);
}
