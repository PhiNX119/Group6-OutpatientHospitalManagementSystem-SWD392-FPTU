package com.swd392.group6.outpatientmanagementsystem.config;

import com.swd392.group6.outpatientmanagementsystem.model.dto.AccountDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Role;
import com.swd392.group6.outpatientmanagementsystem.repository.RoleRepository;
import com.swd392.group6.outpatientmanagementsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final RoleRepository roleRepository;

    private final AccountService accountService;

    @Autowired
    public DataInitializer(RoleRepository roleRepository,
                           AccountService accountService) {
        this.roleRepository = roleRepository;
        this.accountService = accountService;
    }

    @PostConstruct
    public void init() {
        createRoles();
        createRoleAccount("ROLE_ADMIN", "admin", "123");
        createRoleAccount("ROLE_DOCTOR", "doctor", "123");
        createRoleAccount("ROLE_CASHIER_COUNTER_STAFF", "cashier", "123");
        createRoleAccount("ROLE_RECEPTION_COUNTER_STAFF", "reception", "123");
        createRoleAccount("ROLE_PHARMACY_STAFF", "pharmacy", "123");
    }

    private void createRoles() {
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_DOCTOR");
        createRoleIfNotFound("ROLE_CASHIER_COUNTER_STAFF");
        createRoleIfNotFound("ROLE_RECEPTION_COUNTER_STAFF");
        createRoleIfNotFound("ROLE_PHARMACY_STAFF");
    }

    private void createRoleIfNotFound(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            Role newRole = new Role();
            newRole.setName(roleName);
            roleRepository.save(newRole);
        }
    }

    private void createRoleAccount(String roleName, String username, String password) {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername(username);
        accountDto.setPassword(password);
        accountDto.setActive(true);

        if (accountService.findAccountByUsername(accountDto.getUsername()) == null) {
            accountService.saveAccountWithRole(accountDto, roleName);
        }
    }
}
