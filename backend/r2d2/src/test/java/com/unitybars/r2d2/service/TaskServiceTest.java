package com.unitybars.r2d2.service;

import com.unitybars.r2d2.AbstractTest;
import com.unitybars.r2d2.dao.TaskDao;
import com.unitybars.r2d2.dao.TaskFieldValueDao;
import com.unitybars.r2d2.entity.Task;
import com.unitybars.r2d2.entity.TaskFieldValue;
import com.unitybars.r2d2.entity.TaskType;
import com.unitybars.r2d2.entity.TaskTypeField;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by oleg.nestyuk
 * Date: 15-Dec-16.
 */
public class TaskServiceTest extends AbstractTest {

    @Autowired
    private TaskService taskService;

    @Mock
    private TaskDao taskDao;
    @Mock
    private TaskFieldValueDao taskFieldValueDao;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        taskService.setTaskDao(taskDao);
        taskService.setTaskFieldValueDao(taskFieldValueDao);
    }

    @Test
    public void testInit() {
        assertNotNull(taskService);
    }


    @Test
    public void getAllTasksForService() throws Exception {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, 1, TaskType.StatusCode, "200", "Task 1"));
        taskList.add(new Task(2, 1, TaskType.JSON, "200", "Task 2"));
        taskList.add(new Task(3, 1, TaskType.SQLRequest, "", "Task 3"));
        when(taskDao.getTasksForService(1)).thenReturn(taskList);
        assertNotNull(taskDao.getTasksForService(1));
    }

    @Test
    public void getTaskById() throws Exception {
        Task myTask = new Task(1, 1, TaskType.StatusCode, "200", "Task 1");
        TaskTypeField taskTypeField1 = new TaskTypeField(1, TaskType.StatusCode, "Request method", 1, null);
        TaskTypeField taskTypeField2 = new TaskTypeField(2, TaskType.StatusCode, "Header", 0, null);
        TaskFieldValue taskFieldValue1 = new TaskFieldValue(1, 1, taskTypeField1, "GET");
        TaskFieldValue taskFieldValue2 = new TaskFieldValue(2, 1, taskTypeField2, "Content-type:text/plain");
        TaskFieldValue taskFieldValue3 = new TaskFieldValue(3, 1, taskTypeField2, "key:AAAAAA");
        List<TaskFieldValue> taskFieldValues = new ArrayList<TaskFieldValue>();
        taskFieldValues.add(taskFieldValue1);
        taskFieldValues.add(taskFieldValue2);
        taskFieldValues.add(taskFieldValue3);

        when(taskDao.getTaskById(1)).thenReturn(myTask);
        when(taskFieldValueDao.getTaskFieldValuesForTask(1)).thenReturn(taskFieldValues);

        Task task = taskService.getTaskById(1);
        assertNotNull(task);
        assertEquals(1, task.getId());
        assertNotNull(task.getFields());
        assertEquals(3, task.getFields().size());
        assertNotNull("GET", task.getFields().get(0).getValue());
        assertNotNull(task.getFields().get(0).getTaskTypeField());
        assertNotNull(task.getFields().get(1).getTaskTypeField());
        assertEquals(2, task.getFields().get(1).getTaskTypeField().getId());
    }

    @Test
    public void getAllTasksWithFieldsForService() throws Exception {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, 1, TaskType.StatusCode, "200", "Task 1"));
        taskList.add(new Task(2, 1, TaskType.JSON, "200", "Task 2"));
        taskList.add(new Task(3, 1, TaskType.SQLRequest, "", "Task 3"));
        TaskTypeField taskTypeField1 = new TaskTypeField(1, TaskType.StatusCode, "Request method", 1, null);
        TaskTypeField taskTypeField2 = new TaskTypeField(2, TaskType.JSON, "Header", 0, null);
        TaskTypeField taskTypeField3 = new TaskTypeField(3, TaskType.SQLRequest, "Database type", 1, null);
        TaskFieldValue taskFieldValue1 = new TaskFieldValue(1, 1, taskTypeField1, "GET");
        TaskFieldValue taskFieldValue2 = new TaskFieldValue(2, 2, taskTypeField2, "Content-Type:application/json");
        TaskFieldValue taskFieldValue3 = new TaskFieldValue(3, 3, taskTypeField3, "Oracle");
        List<TaskFieldValue> taskFieldValues = new ArrayList<>();
        taskFieldValues.add(taskFieldValue1);
        taskFieldValues.add(taskFieldValue2);
        taskFieldValues.add(taskFieldValue3);

        when(taskDao.getTasksForService(1)).thenReturn(taskList);
        when(taskFieldValueDao.getAllTaskFieldValues()).thenReturn(taskFieldValues);

        List<Task> tasks = taskService.getAllTasksWithFieldsForService(1);
        assertNotNull(tasks);
        assertEquals(3, tasks.size());
        for (int i = 0; i < 3; i++) {
            assertNotNull(tasks.get(i));
            assertNotNull(tasks.get(i).getFields());
            assertEquals(1, tasks.get(i).getFields().size());
            assertEquals(i + 1, tasks.get(i).getFields().get(0).getId());
            assertEquals(i + 1, tasks.get(i).getFields().get(0).getTaskId());
            assertNotNull(tasks.get(i).getFields().get(0).getTaskTypeField());
            assertEquals(i + 1, tasks.get(i).getFields().get(0).getTaskTypeField().getId());
        }
    }

}