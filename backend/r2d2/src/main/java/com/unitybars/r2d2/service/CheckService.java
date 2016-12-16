package com.unitybars.r2d2.service;

import com.unitybars.r2d2.service.executor.CheckExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
@Service
public class CheckService {
    private Logger logger = LoggerFactory.getLogger(CheckService.class);

    @Autowired
    LogService logService;
    @Autowired
    ServiceService serviceService;
    @Autowired
    TaskService taskService;

    public void startCheck() {
        CheckExecutor checkExecutor = new CheckExecutor(logService, serviceService, taskService);
        checkExecutor.start();
    }
}