package com.humber.j2ee.week5.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class CarExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException e) {
        return "exception/404";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String noSuchElementHandler(NoSuchElementException e){
        return "exception/noProductFound";
    }
}