package com.swd392.group6.outpatientmanagementsystem.repository;

import com.swd392.group6.outpatientmanagementsystem.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
