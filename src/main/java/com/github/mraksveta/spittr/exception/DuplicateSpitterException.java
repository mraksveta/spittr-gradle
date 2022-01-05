package com.github.mraksveta.spittr.exception;

public class DuplicateSpitterException extends SpittrApplicationDuplicateException {
    public DuplicateSpitterException() {
    }

    public DuplicateSpitterException(String message) {
        super(message);
    }

    public DuplicateSpitterException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateSpitterException(Throwable cause) {
        super(cause);
    }

    public DuplicateSpitterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
