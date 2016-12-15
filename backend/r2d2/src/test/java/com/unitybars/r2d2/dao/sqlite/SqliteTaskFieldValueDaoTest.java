package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.AbstractTest;
import com.unitybars.r2d2.dao.TaskDao;
import com.unitybars.r2d2.dao.TaskFieldValueDao;
import com.unitybars.r2d2.entity.TaskFieldValue;
import com.unitybars.r2d2.entity.TaskType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public class SqliteTaskFieldValueDaoTest extends AbstractTest {

    @Autowired
    private TaskFieldValueDao taskFieldValueDao;

    @Test
    public void testInit() {
        assertNotNull(taskFieldValueDao);
    }

    @Test
    public void getAllTaskFieldValues() {
        List<TaskFieldValue> taskFieldValues = taskFieldValueDao.getAllTaskFieldValues();
        assertNotNull(taskFieldValues);
        assertEquals(7, taskFieldValues.size());
    }

    @Test
    public void getTaskFieldValuesForTask() {
        List<TaskFieldValue> taskFieldValues = taskFieldValueDao.getTaskFieldValuesForTask(3);
        assertNotNull(taskFieldValues);
        assertEquals(2, taskFieldValues.size());
        assertNotNull(taskFieldValues.get(0).getTaskTypeField());
        assertEquals(1, taskFieldValues.get(0).getId());
        assertEquals(3, taskFieldValues.get(0).getTaskId());
        assertEquals("SELECT * FROM tablename", taskFieldValues.get(0).getValue());
        assertEquals(1, taskFieldValues.get(0).getTaskTypeField().getId());
        assertEquals("Request", taskFieldValues.get(0).getTaskTypeField().getName());
        assertEquals(TaskType.SQLRequest, taskFieldValues.get(0).getTaskTypeField().getTaskType());
        assertEquals(1, taskFieldValues.get(0).getTaskTypeField().getCount());
    }
}