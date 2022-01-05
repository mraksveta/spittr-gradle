package com.github.mraksveta.spittr.exception;

public class PermissionDeniedSpittrApplicationException extends SpittrApplicationException {
    public PermissionDeniedSpittrApplicationException() {
    }

    public PermissionDeniedSpittrApplicationException(String message) {
        super(message);
    }

    public PermissionDeniedSpittrApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionDeniedSpittrApplicationException(Throwable cause) {
        super(cause);
    }

    public PermissionDeniedSpittrApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
