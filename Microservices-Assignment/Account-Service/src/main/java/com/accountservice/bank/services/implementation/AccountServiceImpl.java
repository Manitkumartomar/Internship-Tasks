package com.accountservice.bank.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountservice.bank.entities.Account;
import com.accountservice.bank.entities.external.Transaction;
import com.accountservice.bank.exceptions.ResourceNotFoundException;
import com.accountservice.bank.repositories.AccountRepository;
import com.accountservice.bank.services.AccountService;
import com.accountservice.bank.services.TransactionFeignClient;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionFeignClient transactionFeignClient;

    // Method to create a new account
    @Override
    public Account createAccount(Account account) {
        log.info("Account created {}", account);
        return accountRepository.save(account);
    }

    // Method to retrieve all accounts with associated transactions
    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    // Method to retrieve an account by its ID with associated transactions
    @Override
    public Account getAccountByAccountId(Long accountId) {
        log.info("Received Account from Id: {} ", accountId);
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Account not found with the given accountId: " + accountId));
        // Fetching transactions associated with the account
        account.setTransactions(transactionFeignClient.getTransactions(account.getAccountId()));
        return account;
    }

    // Method to retrieve accounts associated with a given user ID
    @Override
    public List<Account> getAccountByUserId(Long userId) {
        log.info("Accounts found with the userId: {}", userId);

        // Retrieve accounts associated with the provided userId
        List<Account> accounts = accountRepository.findByUserId(userId);

        // Fetch transactions associated with each account using Feign client
        for (Account account : accounts) {
            try {
                List<Transaction> transactions = transactionFeignClient
                        .getTransactions(account.getAccountId());
                account.setTransactions(transactions);
            } catch (FeignException fe) {
                // Handle Feign client exception here, such as logging the error
                log.error("Error occurred while fetching transactions for account with ID {}: {}",
                        account.getAccountId(), fe.getMessage());
                // You might want to throw a custom exception or take other action
            }
        }

        return accounts;
    }

    // Method to retrieve all accounts with associated transactions
    @Override
    public List<Account> getAccounts() {
        List<Account> newAccountList = new ArrayList<>();
        try {
            List<Account> accountList = accountRepository.findAll();
            // Fetching transactions associated with each account
            newAccountList = accountList.stream().map(account -> {
                try {
                    account.setTransactions(transactionFeignClient.getTransactions(account.getAccountId()));
                } catch (FeignException fe) {
                    // Handle Feign client exception here, such as logging the error
                    log.error("Error occurred while fetching transactions for account with ID {}: {}",
                            account.getAccountId(), fe.getMessage());
                }
                return account;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            log.error("Error occurred while fetching accounts: {}", e.getMessage());
        }
        return newAccountList;
    }
}
