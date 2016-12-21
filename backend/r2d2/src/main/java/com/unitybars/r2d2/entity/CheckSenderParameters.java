package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 20-Dec-16.
 */
public class CheckSenderParameters {
    private String mailSubject;
    private String[] mailRecipients;
    private boolean sendMessagesWhenSuccess;

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String[] getMailRecipients() {
        return mailRecipients;
    }

    public void setMailRecipients(String[] mailRecipients) {
        this.mailRecipients = mailRecipients;
    }

    public boolean isSendMessagesWhenSuccess() {
        return sendMessagesWhenSuccess;
    }

    public void setSendMessagesWhenSuccess(boolean sendMessagesWhenSuccess) {
        this.sendMessagesWhenSuccess = sendMessagesWhenSuccess;
    }
}
