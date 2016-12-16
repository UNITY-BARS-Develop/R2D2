package com.unitybars.r2d2.exception;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
public class FunctionalityNotImplemented extends Exception {
    private static final String globalMessage = "This functionality didn't implement";

    public FunctionalityNotImplemented() {
        super(getExceptionMessage());
    }

    public FunctionalityNotImplemented(String message) {
        super(getExceptionMessage(message));
    }

    public FunctionalityNotImplemented(String message, Throwable cause) {
        super(getExceptionMessage(message), cause);
    }

    public FunctionalityNotImplemented(Throwable cause) {
        super(cause);
    }

    protected FunctionalityNotImplemented(String message, Throwable cause, boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(getExceptionMessage(message), cause, enableSuppression, writableStackTrace);
    }

    private static String getExceptionMessage(String message) {
        if (message != null && message.length() > 0) {
            return String.format("%s\n%s", globalMessage, message);
        } else {
            return getExceptionMessage();
        }
    }

    private static String getExceptionMessage() {
        return globalMessage;
    }
}
