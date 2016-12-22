package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.AbstractDaoTest;
import com.unitybars.r2d2.dao.SettingsDao;
import com.unitybars.r2d2.entity.CheckScheduleParameters;
import com.unitybars.r2d2.entity.CheckSenderParameters;
import com.unitybars.r2d2.entity.MailSettings;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
public class SqliteSettingsDaoTest extends AbstractDaoTest {

    @Autowired
    private SettingsDao settingsDao;

    @Test
    public void getMailSettings() throws Exception {
        MailSettings mailSettings = settingsDao.getMailSettings();
        assertNotNull(mailSettings);
        assertEquals("smtp.gmail.com", mailSettings.getHost());
        assertEquals("r2d2.monitorbot@gmail.com", mailSettings.getUsername());
        assertEquals("r2d2d2r2", mailSettings.getPassword());
        assertEquals("587", mailSettings.getPort());
        assertEquals(true, mailSettings.isStartTlsEnable());
    }

    @Test
    public void getCheckSenderParameters() throws Exception {
        CheckSenderParameters checkSenderParameters = settingsDao.getCheckSenderParameters();
        assertNotNull(checkSenderParameters);
        assertEquals("R2D2 - Звіт", checkSenderParameters.getMailSubject());
        assertNotNull(checkSenderParameters.getMailRecipients());
        assertEquals(2, checkSenderParameters.getMailRecipients().length);
        assertEquals("r2d2.monitorbot@gmail.com", checkSenderParameters.getMailRecipients()[0]);
        assertEquals("oleg.nestyuk@unity-bars.com", checkSenderParameters.getMailRecipients()[1]);
    }

    @Test
    public void getCheckScheduleParameters() throws Exception {
        CheckScheduleParameters checkScheduleParameters = settingsDao.getCheckScheduleParameters();
        assertNotNull(checkScheduleParameters);
        assertEquals(true, checkScheduleParameters.isEnableScheduler());
        assertEquals(900000, checkScheduleParameters.getSchedulePeriod());
    }
}