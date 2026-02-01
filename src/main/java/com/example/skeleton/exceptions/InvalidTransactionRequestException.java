package com.example.skeleton.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidTransactionRequestException extends InvalidRequestException {
    public InvalidTransactionRequestException(String error, String message) {
        super(HttpStatus.BAD_REQUEST, error, message);
    }
}