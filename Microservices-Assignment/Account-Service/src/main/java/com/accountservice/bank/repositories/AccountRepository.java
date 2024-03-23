package com.accountservice.bank.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accountservice.bank.entities.Account;

public interface AccountRepository extends JpaRepository<Account,Long>{
    
    List<Account> findByUserId(Long userId);
}
