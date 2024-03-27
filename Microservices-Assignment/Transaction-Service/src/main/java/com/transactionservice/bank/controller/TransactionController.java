package com.transactionservice.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactionservice.bank.entities.Transaction;
import com.transactionservice.bank.services.TransactionService;

import jakarta.validation.Valid;

// Controller class to handle transaction-related HTTP requests
@RestController
@RequestMapping("/transactions")
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  // Endpoint to add a new transaction
  @PostMapping
  public ResponseEntity<Transaction> addTransaction(@RequestBody @Valid Transaction transaction) {
    Transaction transaction1 = transactionService.addTransaction(transaction);
    return ResponseEntity.status(HttpStatus.CREATED).body(transaction1);
  }

  // Endpoint to retrieve transactions associated with a given account ID
  @GetMapping("/{accountId}")
  public ResponseEntity<List<Transaction>> getTransaction(@PathVariable Long accountId) {
    List<Transaction> transactions = transactionService.getTransactions(accountId);
    return ResponseEntity.ok(transactions);
  }
}
