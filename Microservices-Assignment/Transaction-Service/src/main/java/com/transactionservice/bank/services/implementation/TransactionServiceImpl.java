package com.transactionservice.bank.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transactionservice.bank.entities.Transaction;
import com.transactionservice.bank.repositories.TransactionRepository;
import com.transactionservice.bank.services.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction addTransaction(Transaction transaction) {
        Transaction transaction1 = transactionRepository.save(transaction);
        return transaction1;
    }

    @Override
    public List<Transaction> getTransactions(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        return transactions;
    }
    
}
