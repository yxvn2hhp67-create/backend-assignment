package com.example.skeleton.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends InvalidRequestException {
    public UserNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Not Found", "User not found: " + id);
    }
}