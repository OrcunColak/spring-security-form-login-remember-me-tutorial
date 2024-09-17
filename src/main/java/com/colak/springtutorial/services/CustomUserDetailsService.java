package com.colak.springtutorial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRegistrationService regService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return regService.getUserByUsername(username);
    }
}
