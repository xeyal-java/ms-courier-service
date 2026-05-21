package com.example.mscourierservice.exception;

public class CourierNotAvailable extends RuntimeException {
    public CourierNotAvailable(String message) {
        super(message);
    }
}
