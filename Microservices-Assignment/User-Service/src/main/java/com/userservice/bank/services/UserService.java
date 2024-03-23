package com.userservice.bank.services;

import java.util.List;
import com.userservice.bank.entites.User;

public interface UserService {
    
    User createUser(User user);

    List<User> getUsers();

    List<User> getAllUsers();

    User getUserByUserId(Long userId);

    User updateUser(User user);
}
