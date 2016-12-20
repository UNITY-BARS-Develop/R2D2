package com.unitybars.r2d2.service;

import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.service.executor.CheckExecutor;
import com.unitybars.r2d2.service.sender.SenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
@Service
public class CheckService {
    private Logger logger = LoggerFactory.getLogger(CheckService.class);

    private ApplicationContext context;

    @Autowired
    private SenderService senderService;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public void startCheck() {
        CheckExecutor checkExecutor = context.getBean(CheckExecutor.class);
        CheckLog checkLog = checkExecutor.doCheck();
        senderService.sendCheckReport(checkLog);
    }
}