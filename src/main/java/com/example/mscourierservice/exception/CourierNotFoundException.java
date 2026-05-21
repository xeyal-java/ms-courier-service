package com.example.mscourierservice.exception;

public class CourierNotFoundException extends RuntimeException {
    public CourierNotFoundException(String message) {
    super(message);
}
}
