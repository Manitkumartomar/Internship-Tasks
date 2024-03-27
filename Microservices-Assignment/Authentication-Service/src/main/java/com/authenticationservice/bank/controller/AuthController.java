package com.authenticationservice.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.authenticationservice.bank.dto.AuthRequest;
import com.authenticationservice.bank.entity.UserCredential;
import com.authenticationservice.bank.service.AuthService;

// Controller class to handle authentication-related HTTP requests
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Endpoint to register a new user
    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {
        return service.saveUser(user);
    }

    // Endpoint to generate a JWT token
    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        // Authenticating the user
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        // If authentication is successful, generate and return a token
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("Invalid Access!!");
        }
    }

    // Endpoint to validate a JWT token
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        // Validate the token
        service.validateToken(token);
        return "Token is valid";
    }
}
