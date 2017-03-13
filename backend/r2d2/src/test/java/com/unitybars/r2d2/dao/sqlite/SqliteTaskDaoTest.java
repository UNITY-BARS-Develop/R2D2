package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.AbstractDaoTest;
import com.unitybars.r2d2.dao.TaskDao;
import com.unitybars.r2d2.entity.Task;
import com.unitybars.r2d2.entity.TaskTypeId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public class SqliteTaskDaoTest extends AbstractDaoTest {

    @Autowired
    private TaskDao taskDao;

    @Test
    public void testInit() {
        assertNotNull(taskDao);
    }

    @Test
    public void getAllTasks() throws Exception {
        assertNotNull(taskDao.getAllTasks());
        assertEquals(4, taskDao.getAllTasks().size());
    }

    @Test
    public void getTaskById() throws Exception {
        Task task = taskDao.getTaskById("1");
        assertNotNull(task);
        assertEquals("1", task.getId());
        assertEquals("1", task.getServiceId());
        assertEquals("Task 1", task.getName());
        assertEquals("200", task.getExpectedValue());
        assertNull(task.getFields());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getNonExistTaskById() throws Exception {
        taskDao.getTaskById("0");
    }

    @Test
    public void getTasksForService() throws Exception {
        List<Task> tasks = taskDao.getTasksForService("2");
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
        assertEquals("Task Json", tasks.get(0).getName());
        assertEquals(TaskTypeId.JSON, tasks.get(0).getTaskTypeId());
        assertEquals("404", tasks.get(0).getExpectedValue());
        assertEquals("Task StatusCode", tasks.get(1).getName());
        assertEquals(TaskTypeId.StatusCode, tasks.get(1).getTaskTypeId());
        assertEquals("200", tasks.get(1).getExpectedValue());
    }

    @Test
    public void getNotExistTasksForService() throws Exception {
        assertNotNull(taskDao.getTasksForService("0"));
        assertEquals(0, taskDao.getTasksForService("0").size());
    }
}