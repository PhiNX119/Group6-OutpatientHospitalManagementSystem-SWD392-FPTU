package com.swd392.group6.outpatientmanagementsystem.service.impl;

import com.swd392.group6.outpatientmanagementsystem.repository.RoleRepository;
import com.swd392.group6.outpatientmanagementsystem.service.RoleService;
import org.springframework.stereotype.Service;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
