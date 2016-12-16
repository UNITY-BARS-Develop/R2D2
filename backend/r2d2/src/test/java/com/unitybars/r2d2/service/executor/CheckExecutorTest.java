package com.unitybars.r2d2.service.executor;

import com.unitybars.r2d2.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
public class CheckExecutorTest extends AbstractTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void start() throws Exception {
        CheckExecutor checkExecutor = applicationContext.getBean(CheckExecutor.class);
        checkExecutor.start();
    }
}