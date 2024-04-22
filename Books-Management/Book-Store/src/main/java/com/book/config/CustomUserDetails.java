package com.book.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.book.entities.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// Custom UserDetails implementation
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    // Constructor
    public CustomUserDetails(User userCredential) {
        this.username = userCredential.getUsername();
        this.password = userCredential.getPassword();
        this.authorities = Arrays.stream(userCredential.getRole().split(","))
                                 .map(SimpleGrantedAuthority::new)
                                 .collect(Collectors.toList());
    }

    // Method to get authorities (not implemented)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
