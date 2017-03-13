package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.entity.Recipient;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 09-Mar-17.
 */
public interface RecipientDao {
    List<Recipient> getAllRecipients();
    Recipient get(int recipientId);
    void create(Recipient recipient);
    void update(Recipient recipient);
    void delete(int recipientId);
}
