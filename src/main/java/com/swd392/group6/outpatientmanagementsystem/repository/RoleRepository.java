package com.swd392.group6.outpatientmanagementsystem.repository;

import com.swd392.group6.outpatientmanagementsystem.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
