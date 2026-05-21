package com.example.mscourierservice.controller;

import com.example.mscourierservice.exception.CourierNotAvailable;
import com.example.mscourierservice.exception.CourierNotFoundException;
import com.example.mscourierservice.exception.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CourierNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleException(CourierNotFoundException e) {
        return new ErrorResponse("Courier.Not.Found.Exception", e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException e) {
        return new ErrorResponse("Method.Argument.Not.Valid.Exception", e.getMessage());
    }

    @ExceptionHandler(CourierNotAvailable.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleException(CourierNotAvailable e) {
        return new ErrorResponse("Courier.Not.Available.Exception", e.getMessage());
    }

}
