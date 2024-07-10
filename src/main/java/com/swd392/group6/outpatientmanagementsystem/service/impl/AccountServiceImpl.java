package com.swd392.group6.outpatientmanagementsystem.service.impl;

import com.swd392.group6.outpatientmanagementsystem.model.dto.AccountDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.*;
import com.swd392.group6.outpatientmanagementsystem.repository.*;
import com.swd392.group6.outpatientmanagementsystem.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @author phinx
     * @description get user detail
     */
    @Override
    public CustomUserDetails getUserDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails;
        }
        return null;
    }

    /**
     * @author phinx
     * @description insert new account
     */
    @Override
    public void saveAccountWithRole(AccountDto accountDto, String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = checkRoleExist(roleName);
        }

        Account account = new Account();
        account.loadFromDto(accountDto, role);
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        account.setRole(role);

        accountRepository.save(account);
    }

    /**
     * @author phinx
     * @description check role existed or not
     */
    private Role checkRoleExist(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }

    /**
     * @author phinx
     * @description get account by username
     */
    @Override
    public Account findAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    /**
     * @author phinx
     * @description get account list
     */
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findAccountById(int id) { return accountRepository.findById(id); }

    @Override
    public void addNewAccount(AccountDto accountDto) {
        Role role = roleRepository.findByName(accountDto.getRoleName());
        if (role == null) {
            role = checkRoleExist(accountDto.getRoleName());
        }

        Account account = new Account();
        account.loadFromDto(accountDto, role);
        accountRepository.save(account);
    }
}