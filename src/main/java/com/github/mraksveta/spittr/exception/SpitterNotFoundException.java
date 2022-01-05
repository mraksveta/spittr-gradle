package com.github.mraksveta.spittr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SpitterNotFoundException extends SpittrApplicationNotFoundException {
    public SpitterNotFoundException() {
    }

    public SpitterNotFoundException(String message) {
        super(message);
    }

    public SpitterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpitterNotFoundException(Throwable cause) {
        super(cause);
    }

    public SpitterNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
