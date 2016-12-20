package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.SettingsDao;
import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.entity.CheckSenderParameters;
import com.unitybars.r2d2.exception.ContentTransformationException;
import com.unitybars.r2d2.service.executor.CheckExecutor;
import com.unitybars.r2d2.service.sender.MailSender;
import com.unitybars.r2d2.service.sender.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
@Service
public class CheckService {
    private Logger logger = LoggerFactory.getLogger(CheckService.class);

    @Autowired
    private SettingsDao settingsDao;

    private List<Sender> senders;

    @Autowired
    public void setSenders(List<Sender> senders) {
        this.senders = senders;
    }

    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public void startCheck() {
        CheckExecutor checkExecutor = context.getBean(CheckExecutor.class);
        CheckLog checkLog = checkExecutor.doCheck();
        sendCheckResult(checkLog);
    }

    public void sendCheckResult(CheckLog checkLog) {
        CheckSenderParameters checkSenderParameters = settingsDao.getCheckSenderParameters();
        for (Sender sender : senders) {
            try {
                String body = sender.transform(checkLog);
                sender.send(checkSenderParameters.getMailSubject(), body, checkSenderParameters.getMailRecipients());
            } catch (ContentTransformationException e) {
                logger.error("Can't transform CheckLog", e);
            }
        }
    }
}