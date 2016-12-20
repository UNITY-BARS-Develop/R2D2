package com.unitybars.r2d2.service;

import com.unitybars.r2d2.AbstractTest;
import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.entity.CheckStatus;
import com.unitybars.r2d2.entity.ServiceCheckLog;
import com.unitybars.r2d2.entity.TaskCheckLog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oleg.nestyuk
 * Date: 20-Dec-16.
 */
public class CheckServiceTest extends AbstractTest{

    @Autowired
    private CheckService checkService;

    @Test
    public void startCheck() throws Exception {
        // TODO
    }
}