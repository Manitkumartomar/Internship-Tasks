package com.accountservice.bank.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.accountservice.bank.entities.external.Transaction;

// Feign client interface for interacting with the TRANSACTION-SERVICE
@FeignClient(name = "TRANSACTION-SERVICE") // Specifies the name of the Feign client
public interface TransactionFeignClient {

    // Method to retrieve transactions associated with a given account ID
    @GetMapping("/transactions/{accountId}") // Specifies the HTTP GET method and the endpoint
    List<Transaction> getTransactions(@PathVariable Long accountId); // Defines the method signature with a parameter
                                                                     // representing the account ID
}
