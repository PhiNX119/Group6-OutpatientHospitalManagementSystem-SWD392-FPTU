package com.swd392.group6.outpatientmanagementsystem.service;

import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicineDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Medicine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicineService {
    Medicine getMedicineById(Integer id);
    Medicine getMedicineByName(String name);
    List<Medicine> getMedicineList();
    void addNewMedicine(MedicineDto medicineDto);
}