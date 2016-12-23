package com.unitybars.r2d2.configuration;

import com.unitybars.r2d2.constants.Constants;
import com.unitybars.r2d2.entity.CheckScheduleParameters;
import com.unitybars.r2d2.service.CheckService;
import com.unitybars.r2d2.service.SettingsService;
import com.unitybars.r2d2.utils.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by oleg.nestyuk
 * Date: 20-Dec-16.
 */
@Configuration
@EnableScheduling
public class SchedulingConfiguration implements SchedulingConfigurer {
    @Autowired
    private Environment environment;

    @Autowired
    private CheckService checkService;

    @Autowired
    private SettingsService settingsService;

    private Logger logger = LoggerFactory.getLogger(SchedulingConfiguration.class);

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(100);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
        scheduledTaskRegistrar.addTriggerTask(
                () -> {
                    CheckScheduleParameters checkScheduleParameters = settingsService.getCheckScheduleParameters();
                    if (checkScheduleParameters.isEnableScheduler()) {
                        Calendar calendar = Calendar.getInstance();
                        logger.info("Automatic check running at " + Formatter.formatDateTime(calendar));
                        checkService.startCheck();
                    }
                },
                triggerContext -> {
                    CheckScheduleParameters checkScheduleParameters = settingsService.getCheckScheduleParameters();
                    Calendar nextExecutionTime = new GregorianCalendar();
                    Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
                    int period = checkScheduleParameters.getSchedulePeriod() > 0
                            ? checkScheduleParameters.getSchedulePeriod() : Constants.CheckPeriod.DEFAULT_CHECK_PERIOD;
                    nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
                    nextExecutionTime.add(Calendar.MILLISECOND, period);
                    return nextExecutionTime.getTime();
                });
    }
}