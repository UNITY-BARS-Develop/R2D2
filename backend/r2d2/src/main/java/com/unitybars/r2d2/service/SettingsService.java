package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.SettingsDao;
import com.unitybars.r2d2.entity.CheckScheduleParameters;
import com.unitybars.r2d2.entity.CheckSenderParameters;
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

    public CheckSenderParameters getCheckSenderParameters(){
        return settingsDao.getCheckSenderParameters();
    }
}
