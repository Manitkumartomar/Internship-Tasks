package com.userservice.bank.entites.external;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private Long transactionId;
    private Long accountId;
    @CreationTimestamp
    private LocalDateTime timestamp;
    private BigDecimal amount;
    private String transactionType;
    private String transactionStatus;
}
