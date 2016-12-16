package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.CheckLog;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
public interface CheckLogDao {
    long insertCheckLog(CheckLog checkLog);

    CheckLog getCheckLogById(long id);

    List<CheckLog> getAllCheckLogs();
}
