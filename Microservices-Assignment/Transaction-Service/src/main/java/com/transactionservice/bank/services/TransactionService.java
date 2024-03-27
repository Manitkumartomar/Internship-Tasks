package com.transactionservice.bank.services;

import java.util.List;

import com.transactionservice.bank.entities.Transaction;

// Interface defining the contract for transaction-related operations
public interface TransactionService {

    // Method to add a new transaction
    Transaction addTransaction(Transaction transaction);

    // Method to retrieve transactions associated with a given account ID
    List<Transaction> getTransactions(Long accountId);
}
