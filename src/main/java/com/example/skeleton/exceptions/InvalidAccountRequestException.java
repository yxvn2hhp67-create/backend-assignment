package com.example.skeleton.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidAccountRequestException extends InvalidRequestException {
    public InvalidAccountRequestException(String error, String message) {
        super(HttpStatus.BAD_REQUEST, error, message);
    }
}
