package com.unitybars.r2d2.controller;

import com.unitybars.r2d2.entity.CheckScheduleParameters;
import com.unitybars.r2d2.entity.MailSettings;
import com.unitybars.r2d2.exception.InvalidRequestBodyException;
import com.unitybars.r2d2.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by oleg.nestyuk
 * Date: 09-Mar-17.
 */
@RestController
@RequestMapping("/api/v1/settings")
public class SettingsController {
    @Autowired
    private SettingsService settingsService;

    @RequestMapping(value = "mail", method = RequestMethod.GET)
    public MailSettings getMailSettings() {
        return settingsService.getMailSettings();
    }

    @RequestMapping(value = "mail", method = RequestMethod.PUT)
    public void setMailSettings(@RequestBody MailSettings mailSettings) throws InvalidRequestBodyException {
        settingsService.setMailSettings(mailSettings);
    }

    @RequestMapping(value = "scheduler", method = RequestMethod.GET)
    public CheckScheduleParameters getSchedulerSettings() {
        return settingsService.getCheckScheduleParameters();
    }

    @RequestMapping(value = "scheduler", method = RequestMethod.PUT)
    public void setSchedulerSettings(@RequestBody CheckScheduleParameters checkScheduleParameters)
            throws InvalidRequestBodyException {
        settingsService.setCheckScheduleParameters(checkScheduleParameters);
    }
}
