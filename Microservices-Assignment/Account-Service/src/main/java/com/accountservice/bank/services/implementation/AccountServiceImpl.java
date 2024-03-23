package com.accountservice.bank.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountservice.bank.entities.Account;
import com.accountservice.bank.exceptions.ResourceNotFoundException;
import com.accountservice.bank.repositories.AccountRepository;
import com.accountservice.bank.services.AccountService;
import com.accountservice.bank.services.TransactionFeignClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionFeignClient transactionFeignClient;

    @Override
    public Account createAccount(Account account) {
        log.info("Account created {}",account);
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    @Override
    public Account getAccountByAccountId(Long accountId) {
        log.info("Received Account from Id: {} ", accountId);
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with the given accountId: " + accountId));
                account.setTransactions(transactionFeignClient.getTransactions(account.getAccountId()));
        return account;        
    }

    @Override
    public List<Account> getAccountByUserId(Long userId) {
        log.info("Account found with the userId: {}",userId);
        return accountRepository.findByUserId(userId);
    }

    @Override
    public List<Account> getAccounts() {
        List<Account> accountList = accountRepository.findAll();
        List<Account> newAccountList = accountList.stream().map(account-> {
            account.setTransactions(transactionFeignClient.getTransactions(account.getAccountId()));
            return account;
        }).collect(Collectors.toList());
        return newAccountList;
    }

}
