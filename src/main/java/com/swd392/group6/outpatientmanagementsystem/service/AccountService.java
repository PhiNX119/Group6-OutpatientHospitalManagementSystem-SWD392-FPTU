package com.swd392.group6.outpatientmanagementsystem.service;

import com.swd392.group6.outpatientmanagementsystem.model.dto.AccountDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Account;
import com.swd392.group6.outpatientmanagementsystem.model.entity.CustomUserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    CustomUserDetails getUserDetail();

    Account getAccountByUsername(String username);

    Account getAccountById(int id);

    List<Account> getAccountList();

    void addNewAccount(AccountDto accountDto);
}
