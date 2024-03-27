package com.userservice.bank.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userservice.bank.entites.external.Transaction;

// Feign client interface for interacting with the TRANSACTION-SERVICE
@FeignClient(name = "TRANSACTION-SERVICE")
public interface TransactionFeignClient {

    // Method to retrieve transactions associated with a given account ID
    @GetMapping("/transactions/{accountId}")
    List<Transaction> getTransactions(@PathVariable Long accountId);
}
