package com.unitybars.r2d2.service.executor;

import com.unitybars.r2d2.AbstractTest;
import com.unitybars.r2d2.service.CheckService;
import com.unitybars.r2d2.service.LogService;
import com.unitybars.r2d2.service.ServiceService;
import com.unitybars.r2d2.service.TaskService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
public class CheckExecutorTest extends AbstractTest {

    @Autowired
    LogService logService;

    @Autowired
    ServiceService serviceService;

    @Autowired
    TaskService taskService;

    @Test
    public void start() throws Exception {
        CheckExecutor checkExecutor = new CheckExecutor(logService, serviceService, taskService);
        checkExecutor.start();
    }
}