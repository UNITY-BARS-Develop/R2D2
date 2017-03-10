package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.SettingsDao;
import com.unitybars.r2d2.entity.CheckScheduleParameters;
import com.unitybars.r2d2.entity.CheckSenderParameters;
import com.unitybars.r2d2.entity.MailSettings;
import com.unitybars.r2d2.exception.InvalidRequestBodyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by oleg.nestyuk
 * Date: 20-Dec-16.
 */
@Service
public class SettingsService {
    @Autowired
    private SettingsDao settingsDao;

    public void setSettingsDao(SettingsDao settingsDao) {
        this.settingsDao = settingsDao;
    }

    public CheckScheduleParameters getCheckScheduleParameters() {
        return settingsDao.getCheckScheduleParameters();
    }

    public CheckSenderParameters getCheckSenderParameters() {
        return settingsDao.getCheckSenderParameters();
    }

    public MailSettings getMailSettings() {
        return settingsDao.getMailSettings();
    }

    public void setMailSettings(MailSettings mailSettings) throws InvalidRequestBodyException {
        if (isMailSettingsValidForUpdate(mailSettings)) {
            settingsDao.updateMailSettings(mailSettings);
        } else {
            throw new InvalidRequestBodyException();
        }
    }

    public void setCheckScheduleParameters(CheckScheduleParameters checkScheduleParameters) throws InvalidRequestBodyException {
        if (isCheckScheduleParametersValidForUpdate(checkScheduleParameters)) {
            settingsDao.updateScheduleParameters(checkScheduleParameters);
        } else {
            throw new InvalidRequestBodyException();
        }
    }

    private boolean isCheckScheduleParametersValidForUpdate(CheckScheduleParameters checkScheduleParameters) {
        return checkScheduleParameters.getSchedulePeriod() > 0;
    }

    private boolean isMailSettingsValidForUpdate(MailSettings mailSettings) {
        return mailSettings.getHost() != null && mailSettings.getHost().length() > 0
                && mailSettings.getHost() != null && mailSettings.getPassword() != null
                && mailSettings.getPort() != null && Integer.valueOf(mailSettings.getPort()) > 0;
    }
}
