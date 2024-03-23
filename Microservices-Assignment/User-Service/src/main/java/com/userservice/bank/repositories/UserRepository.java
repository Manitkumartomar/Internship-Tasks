package com.userservice.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userservice.bank.entites.User;


public interface UserRepository extends JpaRepository<User,Long>{

    User findByUserId(Long userId);
    
}
