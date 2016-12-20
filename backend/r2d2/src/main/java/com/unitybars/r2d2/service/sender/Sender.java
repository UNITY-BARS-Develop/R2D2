package com.unitybars.r2d2.service.sender;

import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.exception.ContentTransformationException;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
public interface Sender {
    void send(String subject, String message, String[] recipients);

    String transform(CheckLog checkLog) throws ContentTransformationException;
}
