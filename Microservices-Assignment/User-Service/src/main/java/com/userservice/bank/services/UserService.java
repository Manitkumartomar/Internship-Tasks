package com.userservice.bank.services;

import java.util.List;
import com.userservice.bank.entites.User;

// Interface defining the contract for user-related operations
public interface UserService {
    
    // Method to create a new user
    User createUser(User user);

    // Method to retrieve all users
    List<User> getUsers();

    // Method to retrieve all users with associated accounts and transactions
    List<User> getAllUsers();

    // Method to retrieve a user by their ID
    User getUserByUserId(Long userId);

    // Method to update a user's information
    User updateUser(User user);
}
