package com.swd392.group6.outpatientmanagementsystem.service;

import com.swd392.group6.outpatientmanagementsystem.model.dto.AccountDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Account;
import com.swd392.group6.outpatientmanagementsystem.model.entity.CustomUserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    CustomUserDetails getUserDetail();

    void saveAdmin(AccountDto accountDto);

    Account findAccountByUsername(String username);

    List<Account> findAll();
}
