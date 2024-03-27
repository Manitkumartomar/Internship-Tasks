package com.accountservice.bank.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Global exception handler for handling exceptions across the application
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Exception handler for ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFoundHandler(ResourceNotFoundException exception) {

        // Creating a map to hold error details
        Map<String, Object> map = new HashMap<>();
        // Adding error details to the map
        map.put("message", exception.getMessage()); // Error message
        map.put("success", false); // Indicator of unsuccessful operation
        map.put("status", HttpStatus.NOT_FOUND); // HTTP status code
        // Returning the response entity with the error details and appropriate HTTP
        // status code
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
