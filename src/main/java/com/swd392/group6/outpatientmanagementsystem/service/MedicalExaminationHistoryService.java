package com.swd392.group6.outpatientmanagementsystem.service;

import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalExaminationHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalExaminationHistoryService {
    List<MedicalExaminationHistory> GetAll();
}
