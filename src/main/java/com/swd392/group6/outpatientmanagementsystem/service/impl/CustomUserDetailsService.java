package com.swd392.group6.outpatientmanagementsystem.service.impl;

import com.swd392.group6.outpatientmanagementsystem.model.entity.Account;
import com.swd392.group6.outpatientmanagementsystem.model.entity.CustomUserDetails;
import com.swd392.group6.outpatientmanagementsystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        if (account != null && account.isActive()) {
            return new CustomUserDetails(account);
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}