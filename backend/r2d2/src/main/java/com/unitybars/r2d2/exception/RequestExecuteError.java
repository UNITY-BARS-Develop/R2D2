package com.unitybars.r2d2.exception;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
public class RequestExecuteError extends Exception {
    public RequestExecuteError(String url) {
        super(generateErrorText(url));
    }

    public RequestExecuteError(String url, String message) {
        super(generateErrorText(url) + message);
    }

    public RequestExecuteError(String url, String message, Throwable cause) {
        super(generateErrorText(url) + message, cause);
    }

    public RequestExecuteError(String url, Throwable cause) {
        super(generateErrorText(url), cause);
    }

    protected RequestExecuteError(String url, String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(generateErrorText(url) + message, cause, enableSuppression, writableStackTrace);
    }

    private static String generateErrorText(String url) {
        return String.format("Error happened when try to request data form url %s. \n", url);
    }
}
