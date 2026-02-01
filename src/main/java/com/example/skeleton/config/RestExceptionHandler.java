package com.example.skeleton.config;

import com.example.skeleton.exceptions.InvalidRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({InvalidRequestException.class})
    public ResponseEntity<Map<String, Object>> handleNotFound(InvalidRequestException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", ex.getStatus().value());
        body.put("error", ex.getError());
        body.put("message", ex.getMessage());

        return ResponseEntity.status(ex.getStatus()).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            errors.put(fe.getField(), fe.getDefaultMessage());
        }
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", 400);
        body.put("error", "Bad Request");
        body.put("message", "Validation failed");
        body.put("fieldErrors", errors);
        return ResponseEntity.badRequest().body(body);
    }
}
