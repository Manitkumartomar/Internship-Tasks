package com.accountservice.bank.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.accountservice.bank.entities.external.Transaction;

@FeignClient(name = "TRANSACTION-SERVICE")
public interface TransactionFeignClient {

    @GetMapping("/transactions/{accountId}")
    List<Transaction> getTransactions(@PathVariable Long accountId); 
}
