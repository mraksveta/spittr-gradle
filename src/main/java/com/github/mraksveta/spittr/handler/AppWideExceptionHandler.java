package com.github.mraksveta.spittr.handler;

import com.github.mraksveta.spittr.exception.SpittrApplicationDuplicateException;
import com.github.mraksveta.spittr.exception.SpittrApplicationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(SpittrApplicationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundExceptions() {
        return "error/not_found";
    }

    @ExceptionHandler(SpittrApplicationDuplicateException.class)
    @ResponseStatus(HttpStatus.OK)
    public String handleDuplicateExceptions() {
        return "error/duplicate";
    }
}
