package com.unitybars.r2d2.service.sender;

import com.unitybars.r2d2.dao.RecipientDao;
import com.unitybars.r2d2.dao.SettingsDao;
import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.entity.CheckSenderParameters;
import com.unitybars.r2d2.entity.Recipient;
import com.unitybars.r2d2.exception.ContentTransformationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
@Service
public class SenderService {
    private Logger logger = LoggerFactory.getLogger(SenderService.class);

    @Autowired
    private SettingsDao settingsDao;
    @Autowired
    private RecipientDao recipientDao;

    private List<Sender> senders;

    @Autowired
    public void setSenders(List<Sender> senders) {
        this.senders = senders;
    }

    public void setSettingsDao(SettingsDao settingsDao) {
        this.settingsDao = settingsDao;
    }

    public void sendCheckReport(CheckLog checkLog) {
        CheckSenderParameters checkSenderParameters = settingsDao.getCheckSenderParameters();
        List<Recipient> recipients = recipientDao.getAllRecipients();
        for (Sender sender : senders) {
            try {
                String body = sender.transform(checkLog);
                sender.send(checkSenderParameters.getMailSubject(), body, recipients);
            } catch (ContentTransformationException e) {
                logger.error("Can't transform CheckLog", e);
            }
        }
    }
}
