package com.accountservice.bank.controller;

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

import com.accountservice.bank.entities.Account;
import com.accountservice.bank.services.AccountService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account account1 = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(account1);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/transactions")
    @CircuitBreaker(name = "transactionBreaker", fallbackMethod = "transactionBreaker")
    public ResponseEntity<List<Account>> getAccounts(){
        List<Account> accounts = accountService.getAccounts();
        return ResponseEntity.ok(accounts);
    }

    public ResponseEntity<Account> transactionBreaker(Exception exception){
        log.info("Fallback executed because some service is down: ", exception.getMessage());
        Account account = Account.builder()
                                 .accountNo("123456")
                                 .accountStatus("dummy")
                                 .accountType("This is dummy account because some service is down!")
                                 .availableBalance(0.0)
                                 .transactions(null)
                                 .build();
        return ResponseEntity.ok(account);                         
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountByAccountId(@PathVariable Long accountId){
        Account account = accountService.getAccountByAccountId(accountId);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Account>> getAccountByUserId(@PathVariable Long userId){
        List<Account> accounts = accountService.getAccountByUserId(userId);
        return ResponseEntity.ok(accounts);
    }
}
