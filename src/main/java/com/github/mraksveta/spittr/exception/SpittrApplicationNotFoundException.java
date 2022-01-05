package com.github.mraksveta.spittr.exception;

public class SpittrApplicationNotFoundException extends SpittrApplicationException {
    public SpittrApplicationNotFoundException() {
    }

    public SpittrApplicationNotFoundException(String message) {
        super(message);
    }

    public SpittrApplicationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpittrApplicationNotFoundException(Throwable cause) {
        super(cause);
    }

    public SpittrApplicationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
