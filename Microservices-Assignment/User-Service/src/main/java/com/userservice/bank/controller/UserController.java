package com.userservice.bank.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.bank.entites.User;
import com.userservice.bank.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

// Controller class to handle user-related HTTP requests
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userservice;

    // Endpoint to create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        User user1 = userservice.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    // Endpoint to retrieve all users
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> list = userservice.getUsers();
        return ResponseEntity.ok(list);
    }

    // Endpoint to retrieve all users with associated accounts and transactions
    @GetMapping("/account")
    @CircuitBreaker(name = "accountTransactionBreaker", fallbackMethod = "accountTransFallback")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userservice.getAllUsers();
        return ResponseEntity.ok(list);
    }

    // Fallback method for getAllUsers endpoint
    public ResponseEntity<User> accountTransFallback(Exception exception) {
        log.info("Fallback is executed because service is down: ", exception.getMessage());
        User user = User.builder()
                .userId(1L)
                .email("dummy@gmail.com")
                .contactNumber("1234567890")
                .address("This a dummy User because some services are down!")
                .account(null)
                .build();
        return ResponseEntity.ok(user);
    }

    // Endpoint to retrieve a user by their ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userservice.getUserByUserId(userId);
        return ResponseEntity.ok(user);
    }

    // Endpoint to update user information
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User user1 = userservice.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(user1);
    }
}
