package com.apigateway.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Global exception handler for API Gateway
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle MissingAuthorizationHeaderException
    @ExceptionHandler(MissingAuthorizationHeaderException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleMissingAuthorizationHeaderException(MissingAuthorizationHeaderException ex) {
        // Return response with UNAUTHORIZED status and a message indicating missing authorization header
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body("Missing authorization header!");
    }

    // Handle UnauthorizedAccessException
    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
        // Return response with FORBIDDEN status and a message indicating unauthorized access
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                             .body("Unauthorized access to application!");
    }

    // Handle other exceptions if needed
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleException(Exception ex) {
        // Return response with INTERNAL_SERVER_ERROR status and a generic error message
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Internal server error occurred!");
    }
}
