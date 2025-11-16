package com.example.crud_jdbc_app.exception;

public class BadPersonRequestException extends RuntimeException{
    public BadPersonRequestException(String message) {
        super(message);
    }
}
