package com.userservice.bank.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userservice.bank.entites.external.Account;

// Feign client interface for interacting with the ACCOUNT-SERVICE
@FeignClient(name = "ACCOUNT-SERVICE") // Specifies the name of the Feign client
public interface AccountFeignClient {

    // Method to retrieve accounts associated with a given user ID
    @GetMapping("/account/users/{userId}") // Specifies the HTTP GET method and the endpoint
    List<Account> getAccountofUser(@PathVariable Long userId); // Defines the method signature with a parameter
                                                               // representing the user ID
}
