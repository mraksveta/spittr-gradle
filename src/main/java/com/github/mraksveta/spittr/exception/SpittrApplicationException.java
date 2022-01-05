package com.github.mraksveta.spittr.exception;

public class SpittrApplicationException extends RuntimeException {
    public SpittrApplicationException() {
    }

    public SpittrApplicationException(String message) {
        super(message);
    }

    public SpittrApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpittrApplicationException(Throwable cause) {
        super(cause);
    }

    public SpittrApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
