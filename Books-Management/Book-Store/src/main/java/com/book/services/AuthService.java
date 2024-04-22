package com.book.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.book.entities.User;
import com.book.repositories.UserRepo;

// Service class for handling authentication-related operations
@Service
public class AuthService {

    @Autowired
    private UserRepo repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // Method to save user credentials
    public void saveUser(User user) {

        // Check if a user with the same email already exists
        if (repository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User with this email already exists.");
        }
        
        // Encoding user password before saving to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    // Method to generate a JWT token for a given username
    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    // Method to validate a JWT token
    public void validateToken(String token, UserDetails userDetails) {
        jwtService.validateToken(token, userDetails);
    }
}
