package com.unitybars.r2d2.utils;

import com.unitybars.r2d2.entity.*;
import com.unitybars.r2d2.exception.MissedParameterException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
public class ParametersExtractorTest {

    @Test
    public void getRequestMethod() throws Exception {
        RequestMethod requestMethod = ParametersExtractor.getTaskRequestMethod(initStatusCodeTask());
        assertNotNull(requestMethod);
        assertEquals(RequestMethod.GET, requestMethod);
    }

    @Test(expected = MissedParameterException.class)
    public void getNonExistRequestMethod() throws Exception {
        ParametersExtractor.getTaskRequestMethod(initEmptyTask());
    }

    @Test
    public void getHeaders() throws Exception {
        List<HeaderItem> headerItems = ParametersExtractor.getTaskHeaders(initStatusCodeTask());
        assertEquals(2, headerItems.size());
        assertEquals("header_1", headerItems.get(0).getValue());
        assertEquals("header_2", headerItems.get(1).getValue());
    }

    @Test
    public void getNonExistHeaders() throws Exception {
        List<HeaderItem> headerItems = ParametersExtractor.getTaskHeaders(initEmptyTask());
        assertNotNull(headerItems);
        assertEquals(0, headerItems.size());
    }

    @Test
    public void getTaskFieldValues() throws Exception {
        List<TaskFieldValue> taskFieldValues = ParametersExtractor.getTaskFieldValues(initStatusCodeTask(), "request method");
        assertNotNull(taskFieldValues);
        assertEquals(1, taskFieldValues.size());
    }

    @Test
    public void getNoneExistTaskFieldValues() throws Exception {
        List<TaskFieldValue> taskFieldValues = ParametersExtractor.getTaskFieldValues(initEmptyTask(), "non_exist_parameter");
        assertNotNull(taskFieldValues);
        assertEquals(0, taskFieldValues.size());
    }

    @Test
    public void getTaskJsonFieldName() throws Exception {
        String fieldName = ParametersExtractor.getTaskJsonFieldName(initJsonTask());
        assertNotNull(fieldName);
        assertEquals("StatusCode", fieldName);
    }

    @Test
    public void getServiceParameter() throws Exception {
        String parameter = ParametersExtractor.getServiceParameter(initWebService(), ServiceTypeParameter.URL);
        assertNotNull(parameter);
        assertEquals("test_url_1", parameter);
    }

    @Test(expected = MissedParameterException.class)
    public void getNoneExistServiceParameter1() throws Exception {
        ParametersExtractor.getServiceParameter(initEmptyService(), ServiceTypeParameter.URL);
    }

    @Test(expected = MissedParameterException.class)
    public void getNoneExistServiceParameter2() throws Exception {
        ParametersExtractor.getServiceParameter(initEmptyService(), ServiceTypeParameter.CONNECTION_STRING);
    }

    private Task initStatusCodeTask() {
        Task task = new Task("1", "1", TaskType.StatusCode, "200", "Task 1");
        List<TaskFieldValue> taskFieldValues = new ArrayList<>();
        TaskTypeField taskTypeField1 = new TaskTypeField(1, TaskType.StatusCode, "header", 0, null);
        TaskTypeField taskTypeField2 = new TaskTypeField(2, TaskType.StatusCode, "Request method", 1, null);
        taskFieldValues.add(new TaskFieldValue(1, "1", taskTypeField1, "header_1"));
        taskFieldValues.add(new TaskFieldValue(2, "1", taskTypeField1, "header_2"));
        taskFieldValues.add(new TaskFieldValue(3, "1", taskTypeField2, "get"));
        task.setFields(taskFieldValues);
        return task;
    }

    private Task initEmptyTask() {
        return new Task("2", "1", TaskType.StatusCode, "200", "Task 2");
    }

    private Task initJsonTask() {
        Task task = new Task("3", "1", TaskType.JSON, "200", "Task 3");
        List<TaskFieldValue> taskFieldValuesJson = new ArrayList<>();
        TaskTypeField taskTypeField1 = new TaskTypeField(2, TaskType.StatusCode, "Request method", 1, null);
        TaskTypeField taskTypeField2 = new TaskTypeField(3, TaskType.JSON, "json field name", 1, null);
        taskFieldValuesJson.add(new TaskFieldValue(4, "3", taskTypeField1, "GET"));
        taskFieldValuesJson.add(new TaskFieldValue(4, "3", taskTypeField2, "StatusCode"));
        task.setFields(taskFieldValuesJson);
        return task;
    }

    private Service initWebService() {
        Service service = new Service("1", "Service 1", ServiceType.WEB, ServiceStatus.ACTIVE);
        HashMap<ServiceTypeParameter, String> serviceTypeParameter = new HashMap<>();
        serviceTypeParameter.put(ServiceTypeParameter.URL, "test_url_1");
        service.setParameters(serviceTypeParameter);
        return service;
    }

    private Service initEmptyService() {
        return new Service("1", "Service 1", ServiceType.WEB, ServiceStatus.ACTIVE);
    }
}