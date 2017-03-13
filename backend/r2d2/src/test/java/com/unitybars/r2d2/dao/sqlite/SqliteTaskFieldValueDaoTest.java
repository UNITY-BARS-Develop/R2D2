package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.AbstractDaoTest;
import com.unitybars.r2d2.dao.TaskFieldValueDao;
import com.unitybars.r2d2.entity.TaskFieldValue;
import com.unitybars.r2d2.entity.TaskTypeId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public class SqliteTaskFieldValueDaoTest extends AbstractDaoTest {

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
        assertEquals(8, taskFieldValues.size());
    }

    @Test
    public void getTaskFieldValuesForTask() {
        List<TaskFieldValue> taskFieldValues3 = taskFieldValueDao.getTaskFieldValuesForTask("3");
        assertNotNull(taskFieldValues3);
        assertEquals(2, taskFieldValues3.size());
        assertNotNull(taskFieldValues3.get(0).getTaskTypeField());
        assertEquals(1, taskFieldValues3.get(0).getId());
        assertEquals("3", taskFieldValues3.get(0).getTaskId());
        assertEquals("SELECT * FROM tablename", taskFieldValues3.get(0).getValue());
        assertEquals(1, taskFieldValues3.get(0).getTaskTypeField().getId());
        assertEquals("Request", taskFieldValues3.get(0).getTaskTypeField().getName());
        assertEquals(TaskTypeId.SQLRequest, taskFieldValues3.get(0).getTaskTypeField().getTaskTypeId());
        assertEquals(1, taskFieldValues3.get(0).getTaskTypeField().getCount());

        List<TaskFieldValue> taskFieldValues4 = taskFieldValueDao.getTaskFieldValuesForTask("4");
        assertNotNull(taskFieldValues4);
        assertEquals(1, taskFieldValues4.size());
        assertNotNull(taskFieldValues4.get(0).getTaskTypeField());
        assertEquals("GET", taskFieldValues4.get(0).getValue());
    }
}