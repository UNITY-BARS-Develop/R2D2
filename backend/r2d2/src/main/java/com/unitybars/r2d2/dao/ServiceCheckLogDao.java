package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.ServiceCheckLog;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
public interface ServiceCheckLogDao {
    long insertServiceCheckLog(com.unitybars.r2d2.entity.ServiceCheckLog serviceCheckLog);

    com.unitybars.r2d2.entity.ServiceCheckLog getServiceCheckLogById(long id);

    List<ServiceCheckLog> getServiceCheckLogsForCheckLog(long checkLogId);
}
