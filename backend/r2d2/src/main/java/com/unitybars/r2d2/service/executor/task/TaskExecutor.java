package com.unitybars.r2d2.service.executor.task;

import com.unitybars.r2d2.entity.TaskCheckLog;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
public interface TaskExecutor {
    TaskCheckLog doCheck();
}
