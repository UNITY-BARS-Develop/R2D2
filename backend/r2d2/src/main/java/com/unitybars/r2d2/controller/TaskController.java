package com.unitybars.r2d2.controller;

import com.unitybars.r2d2.entity.Task;
import com.unitybars.r2d2.entity.response.TaskIdJson;
import com.unitybars.r2d2.exception.InvalidRequestBody;
import com.unitybars.r2d2.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by oleg.nestyuk
 * Date: 07-Mar-17.
 */
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public TaskIdJson addTask(@RequestBody Task task) throws InvalidRequestBody {
        return new TaskIdJson(taskService.add(task));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public TaskIdJson updateTask(@RequestBody Task task) throws InvalidRequestBody {
        return new TaskIdJson(taskService.updateTask(task));
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Task getTask(@PathVariable("id") String taskId) {
        return taskService.getTaskById(taskId);
    }
}
