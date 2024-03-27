package com.accountservice.bank.services;

import java.util.List;

import com.accountservice.bank.entities.Account;

// Interface defining the contract for account-related operations
public interface AccountService {

    // Method to create a new account
    Account createAccount(Account account);

    // Method to retrieve all accounts
    List<Account> getAccounts();

    // Method to retrieve all accounts with associated details
    List<Account> getAllAccounts();

    // Method to retrieve an account by its ID
    Account getAccountByAccountId(Long accountId);

    // Method to retrieve accounts associated with a given user ID
    List<Account> getAccountByUserId(Long userId);
}
