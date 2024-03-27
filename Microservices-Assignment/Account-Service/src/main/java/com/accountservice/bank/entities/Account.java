package com.accountservice.bank.entities;

import java.util.List;

import com.accountservice.bank.entities.external.Transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "accounts")
public class Account {
    
    @Id
    private Long accountId;
    private Long userId;
    private String accountNo;
    private String accountType;
    private double availableBalance;
    private String accountStatus;

    @Transient
    private List<Transaction> transactions;
    
}
