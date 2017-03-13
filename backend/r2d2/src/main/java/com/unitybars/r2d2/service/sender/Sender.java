package com.unitybars.r2d2.service.sender;

import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.entity.Recipient;
import com.unitybars.r2d2.exception.ContentTransformationException;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
public interface Sender {
    void send(String subject, String message, List<Recipient> recipients);

    String transform(CheckLog checkLog) throws ContentTransformationException;
}
