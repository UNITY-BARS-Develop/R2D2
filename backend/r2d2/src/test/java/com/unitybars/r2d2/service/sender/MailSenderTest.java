package com.unitybars.r2d2.service.sender;

import com.unitybars.r2d2.AbstractTest;
import com.unitybars.r2d2.dao.SettingsDao;
import com.unitybars.r2d2.entity.MailSettings;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
public class MailSenderTest extends AbstractTest {

    @Autowired
    private MailSender mailSender;

    @Mock
    private SettingsDao settingsDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mailSender.setSettingsDao(settingsDao);
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
        // TODO add test
    }

    private MailSettings getMailSettings() {
        return new MailSettings("smtp.gmail.com", "r2d2.monitorbot@gmail.com",
                "r2d2d2r2", "587", true);
    }
}