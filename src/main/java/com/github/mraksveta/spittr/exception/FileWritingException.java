package com.github.mraksveta.spittr.exception;

public class FileWritingException extends SpittrFileException {
    public FileWritingException() {
    }

    public FileWritingException(String message) {
        super(message);
    }

    public FileWritingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileWritingException(Throwable cause) {
        super(cause);
    }

    public FileWritingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
