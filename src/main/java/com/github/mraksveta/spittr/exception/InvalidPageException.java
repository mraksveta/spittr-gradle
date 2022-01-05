package com.github.mraksveta.spittr.exception;

public class InvalidPageException extends SpittrApplicationException {
    public InvalidPageException() {
    }

    public InvalidPageException(String message) {
        super(message);
    }

    public InvalidPageException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPageException(Throwable cause) {
        super(cause);
    }

    public InvalidPageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
