package com.github.mraksveta.spittr.exception;

public class SpittrFileException extends SpittrApplicationException {
    public SpittrFileException() {
    }

    public SpittrFileException(String message) {
        super(message);
    }

    public SpittrFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpittrFileException(Throwable cause) {
        super(cause);
    }

    public SpittrFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
