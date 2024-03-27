package com.authenticationservice.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authenticationservice.bank.entity.UserCredential;
import com.authenticationservice.bank.repository.UserCredentialRepository;

// Service class for handling authentication-related operations
@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // Method to save user credentials
    public String saveUser(UserCredential credential) {
        // Encoding user password before saving to the database
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "User added to the system";
    }

    // Method to generate a JWT token for a given username
    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    // Method to validate a JWT token
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
