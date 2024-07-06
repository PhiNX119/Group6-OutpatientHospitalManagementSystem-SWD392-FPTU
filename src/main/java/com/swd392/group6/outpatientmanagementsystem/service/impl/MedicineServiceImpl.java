package com.swd392.group6.outpatientmanagementsystem.service.impl;

import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicineDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Medicine;
import com.swd392.group6.outpatientmanagementsystem.repository.MedicineRepository;
import com.swd392.group6.outpatientmanagementsystem.service.MedicineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    /**
     * @author phinx
     * @description get medicine by id
     */
    @Override
    public Medicine getMedicineById(Integer id) {
        Optional<Medicine> medicine = medicineRepository.findById(id);
        return medicine.orElse(null);
    }

    /**
     * @author phinx
     * @description get medicine by name
     */
    @Override
    public Medicine getMedicineByName(String name) {
        return medicineRepository.findByName(name);
    }

    /**
     * @author phinx
     * @description get medicine list
     */
    @Override
    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    /**
     * @author phinx
     * @description add new medicine
     */
    @Override
    public void addNewMedicine(MedicineDto medicineDto) {
        Medicine medicine = new Medicine();
        medicine.loadFromDto(medicineDto);
        medicineRepository.save(medicine);
    }
}
