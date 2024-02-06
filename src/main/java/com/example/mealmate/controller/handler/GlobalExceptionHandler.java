package com.example.mealmate.controller.handler;

import com.example.mealmate.dto.ErrorResponse;
import com.example.mealmate.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
        return ErrorResponse
                .builder()
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .build();
    }

    private String getPath(WebRequest request) {
        return (request instanceof ServletWebRequest)
                ? ((ServletWebRequest) request).getRequest().getServletPath()
                : request.getContextPath();
    }
}
