package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.TaskTypeField;
import com.unitybars.r2d2.entity.TaskTypeId;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 07-Mar-17.
 */
public interface TaskTypeFieldDao {
    List<TaskTypeField> getAllTaskTypeFieldsByTaskTypeId(TaskTypeId taskTypeId);
}
