package com.transactionservice.bank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transactionservice.bank.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{

    public List<Transaction> findByAccountId(Long accountId);

}
