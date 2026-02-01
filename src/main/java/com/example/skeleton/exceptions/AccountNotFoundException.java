package com.example.skeleton.exceptions;

import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends InvalidRequestException {
    public AccountNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Not Found", "Account not found: " + id);
    }
}
