package com.userservice.bank.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.userservice.bank.payload.ApiResponse;

// Global exception handler for handling exceptions across the application
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Exception handler for ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex) {
        // Extracting the localized message from the exception
        String message = ex.getLocalizedMessage();
        // Building the API response with the error message, success status, and HTTP
        // status code
        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND)
                .build();
        // Returning the API response along with the appropriate HTTP status code
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }

    // Exception handler for MethodArgumentNotValidException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> invalidArgumentException(MethodArgumentNotValidException exception) {
        // Creating a map to hold field errors
        Map<String, String> errorMap = new HashMap<>();
        // Extracting field errors from the exception and populating the error map
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        // Returning the error map
        return errorMap;
    }
}
