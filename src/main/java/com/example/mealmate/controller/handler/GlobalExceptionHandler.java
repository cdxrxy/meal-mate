package com.example.mealmate.controller.handler;

import com.example.mealmate.dto.ErrorResponse;
import com.example.mealmate.exception.UserAlreadyExistsException;
import com.example.mealmate.exception.UserNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserAlreadyExistsException(UserAlreadyExistsException ex,
                                                          WebRequest request) {
        return ErrorResponse
                .builder()
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(UserNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotExistsException(UserNotExistsException ex,
                                                      WebRequest request) {
        return ErrorResponse
                .builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                               WebRequest request) {
        String errorMessage = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error ->
                        ((FieldError) error).getField() +
                        ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ErrorResponse
                .builder()
                .message(errorMessage)
                .status(HttpStatus.BAD_REQUEST.value())
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
