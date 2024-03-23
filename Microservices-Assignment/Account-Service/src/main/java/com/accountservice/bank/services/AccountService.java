package com.accountservice.bank.services;

import java.util.List;

import com.accountservice.bank.entities.Account;

public interface AccountService {
    
    Account createAccount(Account account);

    List<Account> getAccounts();

    List<Account> getAllAccounts();

    Account getAccountByAccountId(Long accountId);

    List<Account> getAccountByUserId(Long userId);
    
}
