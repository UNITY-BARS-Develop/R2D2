package com.unitybars.r2d2.controller;

import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 10-Mar-17.
 */
@RestController
@RequestMapping("/api/v1/log")
public class CheckLogController {

    @Autowired
    private LogService logService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<CheckLog> getAllCheckLogs() {
        return logService.getAllCheckLogs();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public CheckLog getCheckLog(@PathVariable("id") long checkLogId) {
        return logService.getCheckLogById(checkLogId);
    }
}
