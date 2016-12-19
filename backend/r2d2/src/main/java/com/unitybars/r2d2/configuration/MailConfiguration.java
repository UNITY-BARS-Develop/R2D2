package com.unitybars.r2d2.configuration;

import com.unitybars.r2d2.dao.SettingsDao;
import com.unitybars.r2d2.entity.MailSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
@Configuration
public class MailConfiguration {
    @Autowired
    private SettingsDao settingsDao;

    @Bean
    public JavaMailSender javaMailSender() {
        MailSettings mailSettings = settingsDao.getMailSettings();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.starttls.enable", mailSettings.isStartTlsEnable());
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(mailSettings.getHost());
        mailSender.setPort(Integer.parseInt(mailSettings.getPort()));
        mailSender.setProtocol("smtp");
        mailSender.setUsername(mailSettings.getUsername());
        mailSender.setPassword(mailSettings.getPassword());
        return mailSender;
    }

}
