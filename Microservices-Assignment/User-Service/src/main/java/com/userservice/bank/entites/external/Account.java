package com.userservice.bank.entites.external;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long accountId;
    private String userId;
    private String accountNo;
    private String accountType;
    private double availableBalance;
    private String accountStatus;
    private List<Transaction> transactions;
    
}
