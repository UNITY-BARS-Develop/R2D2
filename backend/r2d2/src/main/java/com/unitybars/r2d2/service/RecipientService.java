package com.unitybars.r2d2.service;

import com.unitybars.r2d2.dao.RecipientDao;
import com.unitybars.r2d2.entity.Recipient;
import com.unitybars.r2d2.exception.InvalidRequestBodyException;
import com.unitybars.r2d2.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 09-Mar-17.
 */
@Service
public class RecipientService {

    @Autowired
    private RecipientDao recipientDao;

    public List<Recipient> getAllRecipients() {
        try {
            return recipientDao.getAllRecipients();
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Recipient getRecipient(int recipientId) {
        return recipientDao.get(recipientId);
    }

    public void addRecipient(Recipient recipient) throws InvalidRequestBodyException {
        if (isRecipientValidForCreate(recipient)) {
            recipientDao.create(recipient);
        } else {
            throw new InvalidRequestBodyException();
        }
    }

    public void updateRecipient(Recipient recipient) throws InvalidRequestBodyException {
        if (isRecipientValidForUpdate(recipient)) {
            recipientDao.update(recipient);
        } else {
            throw new InvalidRequestBodyException();
        }
    }

    public void deleteRecipient(int recipientId) {
        recipientDao.delete(recipientId);
    }

    private boolean isRecipientValidForUpdate(Recipient recipient) {
        return recipient.getId() > 0 && Validator.validateEmail(recipient.getEmail());
    }

    private boolean isRecipientValidForCreate(Recipient recipient) {
        return Validator.validateEmail(recipient.getEmail());
    }
}