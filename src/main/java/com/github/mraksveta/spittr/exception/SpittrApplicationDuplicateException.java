package com.github.mraksveta.spittr.exception;

public class SpittrApplicationDuplicateException extends SpittrApplicationException {
    public SpittrApplicationDuplicateException() {
    }

    public SpittrApplicationDuplicateException(String message) {
        super(message);
    }

    public SpittrApplicationDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpittrApplicationDuplicateException(Throwable cause) {
        super(cause);
    }

    public SpittrApplicationDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
