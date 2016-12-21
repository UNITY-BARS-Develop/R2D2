package com.unitybars.r2d2.service.sender;

import com.unitybars.r2d2.AbstractTest;
import com.unitybars.r2d2.dao.SettingsDao;
import com.unitybars.r2d2.entity.*;
import com.unitybars.r2d2.service.SettingsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
public class MailSenderTest extends AbstractTest {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private SettingsService settingsService;

    @Mock
    private SettingsDao settingsDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        settingsService.setSettingsDao(settingsDao);
        mailSender.setSettingsService(settingsService);
    }

    @Test
    public void send() throws Exception {
        when(settingsDao.getMailSettings()).thenReturn(getMailSettings());
        mailSender.send("Subject", "Fake message text", new String[]{"r2d2.monitorbot@gmail.com"});
    }

    @Test
    public void sendToNoOne() throws Exception {
        when(settingsDao.getMailSettings()).thenReturn(getMailSettings());
        mailSender.send("Subject", "Fake message text", new String[]{});
    }

    @Test
    public void transform() throws Exception {
        List<TaskCheckLog> taskCheckLogList = new ArrayList<>();
        taskCheckLogList.add(new TaskCheckLog(1, "Task 1", "WEB", "200",
                "200", new Date(), CheckStatus.SUCCESS, 1));
        taskCheckLogList.add(new TaskCheckLog(2, "Task 2", "WEB", "200",
                "404", new Date(), CheckStatus.ERROR, 1));
        ServiceCheckLog serviceCheckLog1 = new ServiceCheckLog(1, 1, "Service 1", new Date());
        serviceCheckLog1.setTaskCheckLogs(taskCheckLogList);
        ServiceCheckLog serviceCheckLog2 = new ServiceCheckLog(2, 1, "Service 2", new Date());
        serviceCheckLog2.setTaskCheckLogs(taskCheckLogList);
        List<ServiceCheckLog> serviceCheckLogs = new ArrayList<>();
        serviceCheckLogs.add(serviceCheckLog1);
        serviceCheckLogs.add(serviceCheckLog2);
        CheckLog checkLog = new CheckLog(1, new Date());
        checkLog.setServiceCheckLogs(serviceCheckLogs);

        String template = mailSender.transform(checkLog);

        System.out.println(template);
    }

    private MailSettings getMailSettings() {
        return new MailSettings("smtp.gmail.com", "r2d2.monitorbot@gmail.com",
                "r2d2d2r2", "587", true);
    }
}