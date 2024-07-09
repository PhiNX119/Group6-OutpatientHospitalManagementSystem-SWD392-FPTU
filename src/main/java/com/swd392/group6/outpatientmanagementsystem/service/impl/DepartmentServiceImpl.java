package com.swd392.group6.outpatientmanagementsystem.service.impl;

import com.swd392.group6.outpatientmanagementsystem.model.entity.Department;
import com.swd392.group6.outpatientmanagementsystem.repository.DepartmentRepository;
import com.swd392.group6.outpatientmanagementsystem.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department findDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Override
    public void addNewDepartment(Department department) {
        departmentRepository.save(department);
    }
}
