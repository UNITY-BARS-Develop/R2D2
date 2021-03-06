package com.unitybars.r2d2.service.sender;

import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.entity.MailSettings;
import com.unitybars.r2d2.entity.Recipient;
import com.unitybars.r2d2.exception.ContentTransformationException;
import com.unitybars.r2d2.service.SettingsService;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
@Service
public class MailSender implements Sender {
    @Autowired
    private SettingsService settingsService;
    @Autowired
    private Configuration freemarkerConfiguration;

    private Logger logger = LoggerFactory.getLogger(MailSender.class);

    public void setSettingsService(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @Override
    public void send(String subject, String message, List<Recipient> recipients) {
        if (recipients != null && recipients.size() > 0) {
            try {
                JavaMailSender javaMailSender = createJavaMailSender();
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
                mimeMessage.setContent(message, "text/html; charset=UTF-8");
                helper.setTo(recipients.stream().map(Recipient::getEmail).toArray(String[]::new));
                helper.setSubject(subject);
                helper.setFrom(settingsService.getMailSettings().getUsername());
                javaMailSender.send(mimeMessage);
            } catch (MessagingException e) {
                logger.error("Error happened when try to send message" + e);
            }
        }
    }

    @Override
    public String transform(CheckLog checkLog) throws ContentTransformationException {
        try {
            Map<String, Object> objectsMap = new HashMap<String, Object>() {
                {
                    put("status", checkLog.getStatus());
                    put("checklog", checkLog);
                }
            };
            return FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate("mail.html"), objectsMap);
        } catch (Exception e) {
            logger.error("Exception occurred while processing fmtemplate:", e);
            throw new ContentTransformationException();
        }
    }

    public JavaMailSender createJavaMailSender() {
        MailSettings mailSettings = settingsService.getMailSettings();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        if (mailSettings.isStartTlsEnable()) {
            mailProperties.put("mail.smtp.starttls.enable", true);
        }
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(mailSettings.getHost());
        mailSender.setPort(Integer.parseInt(mailSettings.getPort()));
        mailSender.setProtocol("smtp");
        mailSender.setUsername(mailSettings.getUsername());
        mailSender.setPassword(mailSettings.getPassword());
        return mailSender;
    }
}