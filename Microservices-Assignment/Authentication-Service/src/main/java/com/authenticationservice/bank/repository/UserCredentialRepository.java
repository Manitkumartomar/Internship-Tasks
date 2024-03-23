package com.authenticationservice.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authenticationservice.bank.entity.UserCredential;
import java.util.Optional;


public interface UserCredentialRepository extends JpaRepository<UserCredential,Integer>{

    Optional<UserCredential> findByName(String name);
}
