package com.shamim.eticket.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        // FULL STACK TRACE IN TERMINAL
        logger.error("Unhandled exception occurred", ex);

        // SAFE MESSAGE FOR UI
        String message = (ex.getMessage() != null)
                ? ex.getMessage()
                : "Something went wrong";
        logger.error("Error message for view is: {}", message);
        model.addAttribute("error", message);

        return "shared/error";
    }
}
