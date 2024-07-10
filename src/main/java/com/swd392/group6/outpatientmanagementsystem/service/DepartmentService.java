package com.swd392.group6.outpatientmanagementsystem.service;

import com.swd392.group6.outpatientmanagementsystem.model.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    Department findDepartmentByName(String name);
    List<Department> findAllDepartments();
    void addNewDepartment(Department department);
}
