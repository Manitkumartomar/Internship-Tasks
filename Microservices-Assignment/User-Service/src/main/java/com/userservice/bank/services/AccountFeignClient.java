package com.userservice.bank.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userservice.bank.entites.external.Account;

@FeignClient(name = "ACCOUNT-SERVICE")
public interface AccountFeignClient {
    
    @GetMapping("/account/users/{userId}")
    List<Account> getAccountofUser(@PathVariable Long userId);
}
