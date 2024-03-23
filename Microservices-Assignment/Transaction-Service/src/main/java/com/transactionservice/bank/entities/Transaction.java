package com.transactionservice.bank.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
    
    @Id
    private Long transactionId;
    private Long accountId;
    @CreationTimestamp
    private LocalDateTime timestamp;
    private BigDecimal amount;
    private String transactionType;
    private String transactionStatus;
}
