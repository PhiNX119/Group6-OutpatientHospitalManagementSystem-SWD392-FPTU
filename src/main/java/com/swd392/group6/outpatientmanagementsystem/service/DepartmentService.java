package com.swd392.group6.outpatientmanagementsystem.service;

import com.swd392.group6.outpatientmanagementsystem.model.entity.Department;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    Department findDepartmentByName(String name);

    void addNewDepartment(Department department);
}
