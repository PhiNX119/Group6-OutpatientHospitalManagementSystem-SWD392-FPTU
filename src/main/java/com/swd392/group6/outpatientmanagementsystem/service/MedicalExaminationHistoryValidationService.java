package com.swd392.group6.outpatientmanagementsystem.service;


import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicalExaminationHistoryDto;
import org.springframework.stereotype.Service;

@Service
public interface MedicalExaminationHistoryValidationService {
    String validateFields(MedicalExaminationHistoryDto medicalExaminationHistoryDto);
}
