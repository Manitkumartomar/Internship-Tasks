package com.userservice.bank.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.bank.entites.User;
import com.userservice.bank.entites.external.Account;
import com.userservice.bank.entites.external.Transaction;
import com.userservice.bank.exceptions.ResourceNotFoundException;
import com.userservice.bank.repositories.UserRepository;
import com.userservice.bank.services.AccountFeignClient;
import com.userservice.bank.services.TransactionFeignClient;
import com.userservice.bank.services.UserService;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountFeignClient accountFeignClient;

    @Autowired
    private TransactionFeignClient transactionFeignClient;

    // Method to create a new user
    @Override
    public User createUser(User user) {
        log.info("User created : {}", user);
        return userRepository.save(user);
    }

    // Method to get all registered user
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Method to get all users with associated account and transactions
    @Override
    public List<User> getAllUsers() {
        List<User> newUserList = new ArrayList<>();
        try {
            List<User> userList = userRepository.findAll();
            newUserList = userList.stream().map(user -> {
                try {
                    user.setAccount(accountFeignClient.getAccountofUser(user.getUserId()));
                    // user.setTransaction(transactionFeignClient.getTransactions(user.getUserId()));
                } catch (FeignException fe) {
                    // Handle Feign client exception here, such as logging the error
                    System.err.println(
                            "Error occurred while fetching data for user " + user.getUserId() + ": " + fe.getMessage());
                }
                return user;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            log.info("An exception occurred: " + e.getMessage());
        }
        return newUserList;
    }

    // Method to update a user
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Method to get a user with his id
    @Override
    public User getUserByUserId(Long id) {
        User user = userRepository.findByUserId(id);
        if (user != null) {
            try {
                // Fetch the account associated with the user
                List<Account> userAccount = accountFeignClient.getAccountofUser(user.getUserId());
                user.setAccount(userAccount);

                // Fetch transactions associated with the user's account
                if (userAccount != null) {
                    for (Account account : user.getAccount()) {
                        List<Transaction> transactions = transactionFeignClient.getTransactions(account.getAccountId());
                        account.setTransactions(transactions);
                    }
                }
                log.info("User found with id: {}", id);
                return user;
            } catch (FeignException fe) {
                // Handle Feign client exception here, such as logging the error
                log.error("Error occurred while fetching account or transactions for user with id: {}", id, fe);
                throw new RuntimeException("Error occurred while fetching the other services!");
            }
        } else {
            // Log any exceptions that occur during user retrieval
            log.info("User not found with id: {}", id);
            // Throw a ResourceNotFoundException with a generic error message
            throw new ResourceNotFoundException("User not found with the given id: " + id);
        }
    }

}
