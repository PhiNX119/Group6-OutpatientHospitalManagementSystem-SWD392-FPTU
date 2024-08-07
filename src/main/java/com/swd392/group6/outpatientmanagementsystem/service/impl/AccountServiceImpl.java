package com.swd392.group6.outpatientmanagementsystem.service.impl;

import com.swd392.group6.outpatientmanagementsystem.model.dto.AccountDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.*;
import com.swd392.group6.outpatientmanagementsystem.repository.*;
import com.swd392.group6.outpatientmanagementsystem.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
     * @description insert default user - admin
     */
    @Override
    public void saveAdmin(AccountDto accountDto) {
        // insert admin account first
        Account account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        account.setActive(accountDto.isActive());
        accountRepository.save(account);

        // then set role admin for admin
        Account updateAccount = accountRepository.findByUsername(account.getUsername());

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist("ROLE_ADMIN");
        }
        updateAccount.setRoles(Arrays.asList(role));
        accountRepository.save(updateAccount);
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
     * @description get user detail by username
     */
    @Override
    public Account findAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    /**
     * @author phinx
     * @description get user list
     */
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}