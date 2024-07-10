package com.swd392.group6.outpatientmanagementsystem.service.impl;

import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicalExaminationHistoryDto;
import com.swd392.group6.outpatientmanagementsystem.repository.AccountRepository;
import com.swd392.group6.outpatientmanagementsystem.repository.MedicalRecordRepository;
import com.swd392.group6.outpatientmanagementsystem.repository.PatientInfoRepository;
import com.swd392.group6.outpatientmanagementsystem.service.MedicalExaminationHistoryValidationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalExaminationHistoryValidationServiceImpl implements MedicalExaminationHistoryValidationService {
    private final MedicalRecordRepository mrRepo;
    private final AccountRepository accRepo;
    private final PatientInfoRepository piRepo;
    public MedicalExaminationHistoryValidationServiceImpl(MedicalRecordRepository mrRepo,
                                                          AccountRepository accRepo,
                                                          PatientInfoRepository piRepo) {
        this.accRepo = accRepo;
        this.mrRepo = mrRepo;
        this.piRepo = piRepo;
    }
    @Override
    public String ValidateFields(MedicalExaminationHistoryDto medicalExaminationHistoryDto) {
        List<String> errors = new ArrayList<>();
        if (medicalExaminationHistoryDto.getStaffId() == 0 || accRepo.findById(medicalExaminationHistoryDto.getStaffId()).isEmpty()) {
            errors.add("Staff is not exist!");
        }
        if (medicalExaminationHistoryDto.getDescription().trim().isBlank()) {
            errors.add("Description can not be blank!");
        }
        if (medicalExaminationHistoryDto.getCreatedDate().trim().isBlank()) {
            errors.add("Created date can not be blank!");
        }
        if (medicalExaminationHistoryDto.getPatientId() == 0 || piRepo.findById(medicalExaminationHistoryDto.getPatientId()).isEmpty()) {
            errors.add("Patient is not exist!");
        }
        StringBuilder result = new StringBuilder();
        if (errors.isEmpty()) {
            return "success || Add Medical Examination History Successfully!";
        } else {
            result.append("error || ");
            for (int i = 0; i < errors.size(); i++) {
                result.append(errors.get(i)).append(" || ");
            }
        }
        return result.toString();
    }
}
