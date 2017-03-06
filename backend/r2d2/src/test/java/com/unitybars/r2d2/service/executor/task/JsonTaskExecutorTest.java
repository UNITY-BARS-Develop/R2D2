package com.unitybars.r2d2.service.executor.task;

import com.google.gson.JsonObject;
import com.unitybars.r2d2.AbstractTest;
import com.unitybars.r2d2.constants.Constants;
import com.unitybars.r2d2.entity.*;
import com.unitybars.r2d2.service.RequestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by oleg.nestyuk
 * Date: 21-Dec-16.
 */
public class JsonTaskExecutorTest extends AbstractTest {
    @Autowired
    private RequestService requestService;

    @Autowired
    private JsonTaskExecutor jsonTaskExecutor;

    @Test
    public void setTask() throws Exception {
        jsonTaskExecutor.setTask(getTask());
        Field field = jsonTaskExecutor.getClass().getDeclaredField("task");
        field.setAccessible(true);
        Task task = (Task) field.get(jsonTaskExecutor);
        assertNotNull(task);
        assertEquals("7", task.getId());
        assertEquals("1", task.getExpectedValue());
        assertEquals("Task test", task.getName());
    }

    @Test
    public void setService() throws Exception {
        jsonTaskExecutor.setService(getService());
        Field field = jsonTaskExecutor.getClass().getDeclaredField("service");
        field.setAccessible(true);
        Service service = (Service) field.get(jsonTaskExecutor);
        assertNotNull(service);
        assertEquals("45", service.getId());
        assertEquals("Service test", service.getName());
    }

    @Test
    public void doCheck() throws Exception {
        jsonTaskExecutor.setTask(getTask());
        jsonTaskExecutor.setService(getService());
        TaskCheckLog taskCheckLog = jsonTaskExecutor.doCheck();
        assertNotNull(taskCheckLog);
        assertEquals("1", taskCheckLog.getResultValue());
        assertEquals(CheckStatus.SUCCESS, taskCheckLog.getCheckStatus());
        assertEquals("Task test", taskCheckLog.getTaskName());
    }

    @Test
    public void doErrorCheck() throws Exception {
        Task task = getTask();
        task.setExpectedValue("2");
        jsonTaskExecutor.setTask(task);
        jsonTaskExecutor.setService(getService());
        TaskCheckLog taskCheckLog = jsonTaskExecutor.doCheck();
        assertNotNull(taskCheckLog);
        assertEquals("2", taskCheckLog.getExpectedValue());
        assertEquals("1", taskCheckLog.getResultValue());
        assertEquals(CheckStatus.ERROR, taskCheckLog.getCheckStatus());
        assertEquals("Task test", taskCheckLog.getTaskName());
    }

    @Test
    public void parseJson() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = jsonTaskExecutor.getClass().getDeclaredMethod("parseJson", String.class);
        method.setAccessible(true);
        String jsonStr = "{name: test, value: 10}";
        JsonObject jsonObject = (JsonObject) method.invoke(jsonTaskExecutor, jsonStr);
        assertNotNull(jsonObject);
        assertEquals("test", jsonObject.get("name").getAsString());
        assertEquals(10, jsonObject.get("value").getAsInt());
    }

    private Task getTask() {
        Task task = new Task("7", "1", TaskType.JSON, "1", "Task test");
        TaskTypeField taskTypeField1 = new TaskTypeField(1, TaskType.JSON,
                Constants.TaskTypeFieldConstants.REQUEST_METHOD, 1, null);
        TaskTypeField taskTypeField2 = new TaskTypeField(2, TaskType.JSON,
                Constants.TaskTypeFieldConstants.JSON_FIELD_NAME, 1, null);
        TaskFieldValue taskFieldValue1 = new TaskFieldValue(1, "1", taskTypeField1, "GET");
        TaskFieldValue taskFieldValue2 = new TaskFieldValue(2, "1", taskTypeField2, "id");
        List<TaskFieldValue> taskFieldValues = new ArrayList<>();
        taskFieldValues.add(taskFieldValue1);
        taskFieldValues.add(taskFieldValue2);
        task.setFields(taskFieldValues);
        return task;
    }

    private Service getService() {
        List<Service> services = new ArrayList<>();
        Service service = new Service("45", "Service test", ServiceType.WEB, ServiceStatus.ACTIVE);
        List<ServiceTypeParameterValue> parameters = new ArrayList<>();
        parameters.add(new ServiceTypeParameterValue(1, "1", ServiceTypeParameter.URL,
                "https://jsonplaceholder.typicode.com/posts/1"));
        service.setParametersFromList(parameters);
        return service;
    }

}