package com.example.mscourierservice.exception;

import com.example.mscourierservice.controller.ErrorResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.example.mscourierservice.exception.ErrorCodes.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CourierNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleException(CourierNotFoundException e) {
        return new ErrorResponse(COURIER_NOT_FOUND, e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException e) {
        return new ErrorResponse(Method_ARGUMENT_NOT_VALID, e.getMessage());
    }

    @ExceptionHandler(CourierNotAvailable.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleException(CourierNotAvailable e) {
        return new ErrorResponse(COURIER_NOT_AVAILABLE, e.getMessage());
    }

}
