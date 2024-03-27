package com.authenticationservice.bank.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.authenticationservice.bank.entity.UserCredential;

import java.util.Collection;

// Custom UserDetails implementation
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;

    // Constructor
    public CustomUserDetails(UserCredential userCredential) {
        this.username = userCredential.getName();
        this.password = userCredential.getPassword();
    }

    // Method to get authorities (not implemented)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    // Method to get the user's password
    @Override
    public String getPassword() {
        return password;
    }

    // Method to get the username
    @Override
    public String getUsername() {
        return username;
    }

    // Method to check if the account is non-expired
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Method to check if the account is non-locked
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Method to check if the credentials are non-expired
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Method to check if the account is enabled
    @Override
    public boolean isEnabled() {
        return true;
    }
}
