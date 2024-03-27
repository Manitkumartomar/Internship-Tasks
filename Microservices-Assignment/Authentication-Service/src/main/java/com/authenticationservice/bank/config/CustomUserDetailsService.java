package com.authenticationservice.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.authenticationservice.bank.entity.UserCredential;
import com.authenticationservice.bank.repository.UserCredentialRepository;

import java.util.Optional;

// Service for loading user-specific data during authentication
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository repository;

    // Method to load user by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find user credentials by username
        Optional<UserCredential> credential = repository.findByName(username);
        // If user credentials found, return CustomUserDetails object, otherwise throw
        // UsernameNotFoundException
        return credential.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with name: " + username));
    }
}
