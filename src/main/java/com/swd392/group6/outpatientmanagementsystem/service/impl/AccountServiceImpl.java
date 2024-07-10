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
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository,
                              RoleRepository roleRepository, DepartmentRepository departmentRepository,
                              PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
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
     * @description get account by username
     */
    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Account getAccountById(int id) {
        return accountRepository.findById(id).orElse(null);
    }

    /**
     * @author phinx
     * @description get account list
     */
    @Override
    public List<Account> getAccountList() {
        return accountRepository.findAll();
    }

    @Override
    public void addNewAccount(AccountDto accountDto) {
        Role role = roleRepository.findByName(accountDto.getRoleName());

        Department department = departmentRepository.findByName(accountDto.getDepartmentName());

        Account account = new Account();
        account.loadFromDto(accountDto);
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        account.setRole(role);
        account.setDepartment(department);

        accountRepository.save(account);
    }
}