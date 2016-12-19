package com.unitybars.r2d2.service.sender;

import com.unitybars.r2d2.dao.SettingsDao;
import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.entity.MailSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
@Service
public class MailSender implements Sender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SettingsDao settingsDao;

    private MailSettings mailSettings;

    public void setSettingsDao(SettingsDao settingsDao) {
        this.settingsDao = settingsDao;
    }

    @Override
    public void send(String subject, String message, String[] recipients) {
        if (recipients != null && recipients.length > 0){
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(recipients);
            mailMessage.setFrom(getMailSettings().getUsername());
            mailMessage.setSubject(subject);
            mailMessage.setText(message);
            javaMailSender.send(mailMessage);
        }
    }

    @Override
    public String transform(CheckLog checkLog) {
        return null;
    }

    private MailSettings getMailSettings() {
        if (mailSettings == null) {
            mailSettings = settingsDao.getMailSettings();
        }
        return mailSettings;
    }
}