package com.github.mraksveta.spittr.exception;

public class FileReadingException extends SpittrFileException {
    public FileReadingException() {
    }

    public FileReadingException(String message) {
        super(message);
    }

    public FileReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileReadingException(Throwable cause) {
        super(cause);
    }

    public FileReadingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
