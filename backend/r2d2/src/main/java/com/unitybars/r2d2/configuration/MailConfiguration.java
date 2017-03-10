package com.unitybars.r2d2.configuration;

import com.unitybars.r2d2.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
@Configuration
public class MailConfiguration {
    @Autowired
    private SettingsService settingsService;

    @Bean
    @Primary
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
        freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("classpath:/templates/");
        return freeMarkerConfigurationFactoryBean;
    }
}
