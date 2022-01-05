package com.github.mraksveta.spittr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SpittlesNotFoundException extends SpittrApplicationNotFoundException {
    public SpittlesNotFoundException() {
    }

    public SpittlesNotFoundException(String message) {
        super(message);
    }

    public SpittlesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpittlesNotFoundException(Throwable cause) {
        super(cause);
    }

    public SpittlesNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
