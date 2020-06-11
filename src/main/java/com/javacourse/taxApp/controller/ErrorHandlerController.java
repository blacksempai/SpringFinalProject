package com.javacourse.taxApp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
@Order(2)
public class ErrorHandlerController {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus()
    public String notFoundPage(Throwable e) {
        log.warn(e.getMessage(), e);
        return "error";
    }

}
