package com.example.skeleton.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends RuntimeException {

    private final HttpStatus status;
    private final String error;
    private final String message;

    public InvalidRequestException(HttpStatus status, String error, String message) {
        super(message);

        this.status = status;
        this.error = error;
        this.message = message;
    }

    public HttpStatus getStatus() { return this.status; }

    public String getError() { return this.error; }

    public String getMessage() { return this.message; }
}
