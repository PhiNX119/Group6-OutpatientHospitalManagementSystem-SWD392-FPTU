package com.swd392.group6.outpatientmanagementsystem.service.impl;

import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalExaminationHistory;
import com.swd392.group6.outpatientmanagementsystem.repository.MedicalExaminationHistoryRepository;
import com.swd392.group6.outpatientmanagementsystem.service.MedicalExaminationHistoryService;

import java.util.List;

public class MedicalExaminationHistoryServiceImpl implements MedicalExaminationHistoryService {

    private final MedicalExaminationHistoryRepository repo;

    public MedicalExaminationHistoryServiceImpl(MedicalExaminationHistoryRepository repo) {this.repo = repo;}


    @Override
    public List<MedicalExaminationHistory> GetAll() {
        return repo.findAll();
    }
}
