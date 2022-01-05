package com.github.mraksveta.spittr.exception;

public class DuplicateSpittleException extends SpittrApplicationDuplicateException {
    public DuplicateSpittleException() {
    }

    public DuplicateSpittleException(String message) {
        super(message);
    }

    public DuplicateSpittleException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateSpittleException(Throwable cause) {
        super(cause);
    }

    public DuplicateSpittleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
