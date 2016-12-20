package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.CheckSenderParameters;
import com.unitybars.r2d2.entity.MailSettings;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
public interface SettingsDao {
    MailSettings getMailSettings();

    CheckSenderParameters getCheckSenderParameters();
}
