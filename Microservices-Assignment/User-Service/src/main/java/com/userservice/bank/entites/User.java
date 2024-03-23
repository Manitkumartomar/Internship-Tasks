package com.userservice.bank.entites;

import java.util.List;

import com.userservice.bank.entites.external.Account;
import com.userservice.bank.entites.external.Transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "users")
public class User {
    
    @Id
    private Long userId;
    @NotBlank(message = "Username shouldn't be null!")
    private String username;
    @Email(message = "Please enter a valid email address!")
    private String email;
    @Pattern(regexp = "^\\d{10}$", message = "Invalid number!")
    private String contactNumber;
    @NotBlank(message = "Address shouldn't be null!")
    private String address;

    @Transient
    private List<Account> account;

    @Transient
    private List<Transaction> transaction;
}
