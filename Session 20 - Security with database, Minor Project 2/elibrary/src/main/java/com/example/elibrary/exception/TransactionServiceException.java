package com.example.elibrary.exception;

import com.example.elibrary.service.TransactionService;

public class TransactionServiceException extends RuntimeException {
    public TransactionServiceException(String message) {
        super(message);
    }
}
