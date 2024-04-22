package com.book.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.book.dto.AuthRequest;
import com.book.entities.User;
import com.book.services.AuthService;

// Controller class to handle authentication-related HTTP requests
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Endpoint to register a new user
    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        try {
            service.saveUser(user);
            return ResponseEntity.ok("User registered successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed: " + e.getMessage());
        }
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
        service.validateToken(token, null);
        return "Token is valid";
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUserProfile(){
        List<User> users = service.getUsers();
       return ResponseEntity.ok(users);
    }
    
}
