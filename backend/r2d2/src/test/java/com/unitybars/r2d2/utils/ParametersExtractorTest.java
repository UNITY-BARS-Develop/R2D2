package com.unitybars.r2d2.utils;

import com.unitybars.r2d2.entity.*;
import com.unitybars.r2d2.exception.MissedParameterException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
public class ParametersExtractorTest {
    private Task task1;
    private Task task2;

    @Before
    public void init() {
        task1 = new Task(1, 1, TaskType.StatusCode, "200", "Task 1");
        task2 = new Task(2, 1, TaskType.StatusCode, "200", "Task 2");
        List<TaskFieldValue> taskFieldValues = new ArrayList<>();
        TaskTypeField taskTypeField1 = new TaskTypeField(1, TaskType.StatusCode, "header", 0, null);
        TaskTypeField taskTypeField2 = new TaskTypeField(1, TaskType.StatusCode, "Request method", 0, null);
        taskFieldValues.add(new TaskFieldValue(1, 1, taskTypeField1, "header_1"));
        taskFieldValues.add(new TaskFieldValue(2, 1, taskTypeField1, "header_2"));
        taskFieldValues.add(new TaskFieldValue(3, 1, taskTypeField2, "get"));
        task1.setFields(taskFieldValues);
    }

    @Test
    public void getRequestMethod() throws Exception {
        RequestMethod requestMethod = ParametersExtractor.getTaskRequestMethod(task1);
        assertNotNull(requestMethod);
        assertEquals(RequestMethod.GET, requestMethod);
    }

    @Test(expected = MissedParameterException.class)
    public void getNonExistRequestMethod() throws Exception {
        ParametersExtractor.getTaskRequestMethod(task2);
    }

    @Test
    public void getHeaders() throws Exception {
        List<HeaderItem> headerItems = ParametersExtractor.getTaskHeaders(task1);
        assertEquals(2, headerItems.size());
        assertEquals("header_1", headerItems.get(0).getValue());
        assertEquals("header_2", headerItems.get(1).getValue());
    }

    @Test
    public void getNonExistHeaders() throws Exception {
        List<HeaderItem> headerItems = ParametersExtractor.getTaskHeaders(task2);
        assertNotNull(headerItems);
        assertEquals(0, headerItems.size());
    }

    @Test
    public void getTaskFieldValues() throws Exception {
        List<TaskFieldValue> taskFieldValues = ParametersExtractor.getTaskFieldValues(task1, "request method");
        assertNotNull(taskFieldValues);
        assertEquals(1, taskFieldValues.size());
    }

    @Test()
    public void getNoneExistTaskFieldValues() throws Exception {
        List<TaskFieldValue> taskFieldValues = ParametersExtractor.getTaskFieldValues(task2, "non_exist_parameter");
        assertNotNull(taskFieldValues);
        assertEquals(0, taskFieldValues.size());
    }

}