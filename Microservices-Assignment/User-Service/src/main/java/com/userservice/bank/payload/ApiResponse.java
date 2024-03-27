package com.userservice.bank.payload;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Class representing an API response
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

    private String message; // Message to be included in the API response
    private boolean success; // Indicator of whether the operation was successful
    private HttpStatus status; // HTTP status code of the response
}
