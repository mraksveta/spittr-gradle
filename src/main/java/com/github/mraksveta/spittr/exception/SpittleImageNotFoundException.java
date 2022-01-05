package com.github.mraksveta.spittr.exception;

public class SpittleImageNotFoundException extends SpittrApplicationNotFoundException {
    public SpittleImageNotFoundException() {
    }

    public SpittleImageNotFoundException(String message) {
        super(message);
    }

    public SpittleImageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpittleImageNotFoundException(Throwable cause) {
        super(cause);
    }

    public SpittleImageNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
