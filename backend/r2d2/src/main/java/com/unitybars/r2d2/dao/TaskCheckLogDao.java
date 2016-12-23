package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.TaskCheckLog;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
public interface TaskCheckLogDao {
    long insertTaskCheckLog(TaskCheckLog taskCheckLog);

    TaskCheckLog getTaskCheckLogById(long id);

    List<TaskCheckLog> getTaskCheckLogsForServiceCheckLog(long serviceCheckLogId);
}
