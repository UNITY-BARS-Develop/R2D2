package com.unitybars.r2d2.service.executor;

import com.unitybars.r2d2.AbstractTest;
import com.unitybars.r2d2.entity.Task;
import com.unitybars.r2d2.entity.TaskTypeId;
import com.unitybars.r2d2.service.executor.task.JsonTaskExecutorCreator;
import com.unitybars.r2d2.service.executor.task.StatusCodeTaskExecutorCreator;
import com.unitybars.r2d2.service.executor.task.TaskExecutorCreator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
public class CheckExecutorTest extends AbstractTest {

    @Autowired
    private ApplicationContext applicationContext;

    private CheckExecutor checkExecutor;

    @Before
    public void init() throws NoSuchMethodException {
        checkExecutor = applicationContext.getBean(CheckExecutor.class);
    }

    @Test
    public void doCheck() throws Exception {
        CheckExecutor checkExecutor = applicationContext.getBean(CheckExecutor.class);
        checkExecutor.doCheck();
    }

    @Test
    public void getTaskExecutorCreatorJson() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getTaskExecutorCreatorMethod = checkExecutor.getClass()
                .getDeclaredMethod("getTaskExecutorCreator", Task.class);
        getTaskExecutorCreatorMethod.setAccessible(true);

        Task taskJson = new Task("1", "1", TaskTypeId.JSON, "1", "1");
        TaskExecutorCreator taskExecutorCreator =
                (TaskExecutorCreator) getTaskExecutorCreatorMethod.invoke(checkExecutor, taskJson);
        assertNotNull(taskExecutorCreator);
        assertTrue(taskExecutorCreator instanceof JsonTaskExecutorCreator);
    }

    @Test
    public void getTaskExecutorCreatorStatusCode() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getTaskExecutorCreatorMethod = checkExecutor.getClass()
                .getDeclaredMethod("getTaskExecutorCreator", Task.class);
        getTaskExecutorCreatorMethod.setAccessible(true);

        Task taskJson = new Task("1", "1", TaskTypeId.StatusCode, "1", "1");
        TaskExecutorCreator taskExecutorCreator =
                (TaskExecutorCreator) getTaskExecutorCreatorMethod.invoke(checkExecutor, taskJson);
        assertNotNull(taskExecutorCreator);
        assertTrue(taskExecutorCreator instanceof StatusCodeTaskExecutorCreator);
    }

    @Test(expected = InvocationTargetException.class)
    public void getTaskExecutorCreatorSqlRequest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getTaskExecutorCreatorMethod = checkExecutor.getClass()
                .getDeclaredMethod("getTaskExecutorCreator", Task.class);
        getTaskExecutorCreatorMethod.setAccessible(true);

        Task taskJson = new Task("1", "1", TaskTypeId.SQLRequest, "1", "1");
        TaskExecutorCreator taskExecutorCreator =
                (TaskExecutorCreator) getTaskExecutorCreatorMethod.invoke(checkExecutor, taskJson);
    }
}