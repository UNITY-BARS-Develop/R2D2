package com.unitybars.r2d2.exception;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
public class MissedParameterException extends Exception {

    public MissedParameterException(String missedParameterName) {
        super(generateErrorText(missedParameterName));
    }

    public MissedParameterException(String missedParameterName, String message) {
        super(generateErrorText(missedParameterName) + message);
    }

    public MissedParameterException(String missedParameterName, String message, Throwable cause) {
        super(generateErrorText(missedParameterName) + message, cause);
    }

    public MissedParameterException(String missedParameterName, Throwable cause) {
        super(generateErrorText(missedParameterName), cause);
    }

    protected MissedParameterException(String missedParameterName, String message, Throwable cause,
                                       boolean enableSuppression, boolean writableStackTrace) {
        super(generateErrorText(missedParameterName) + message, cause, enableSuppression, writableStackTrace);
    }

    private static String generateErrorText(String missedParameterName) {
        return String.format("Parameter %s required. \n", missedParameterName);
    }

}