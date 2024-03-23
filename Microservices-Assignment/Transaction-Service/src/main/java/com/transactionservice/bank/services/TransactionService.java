package com.transactionservice.bank.services;

import java.util.List;

import com.transactionservice.bank.entities.Transaction;

public interface TransactionService {
    
    Transaction addTransaction(Transaction transaction);

    List<Transaction> getTransactions(Long accountId);
}
