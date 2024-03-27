package com.userservice.bank.exceptions;

// Custom exception class to represent resource not found errors
public class ResourceNotFoundException extends RuntimeException {

    // Default constructor with a generic error message
    public ResourceNotFoundException() {
        super("Resource not found!");
    }

    // Constructor with a custom error message
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
