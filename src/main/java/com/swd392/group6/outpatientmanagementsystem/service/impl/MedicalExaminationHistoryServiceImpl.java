package com.swd392.group6.outpatientmanagementsystem.service.impl;

import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicalExaminationHistoryDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.*;
import com.swd392.group6.outpatientmanagementsystem.repository.AccountRepository;
import com.swd392.group6.outpatientmanagementsystem.repository.MedicalExaminationHistoryRepository;
import com.swd392.group6.outpatientmanagementsystem.repository.MedicalRecordRepository;
import com.swd392.group6.outpatientmanagementsystem.repository.PatientInfoRepository;
import com.swd392.group6.outpatientmanagementsystem.service.AccountService;
import com.swd392.group6.outpatientmanagementsystem.service.MedicalExaminationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalExaminationHistoryServiceImpl implements MedicalExaminationHistoryService {
    private final MedicalExaminationHistoryRepository medicalExaminationHistoryRepository;
    private final PatientInfoRepository patientInfoRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public MedicalExaminationHistoryServiceImpl(MedicalExaminationHistoryRepository medicalExaminationHistoryRepository,
                                                PatientInfoRepository patientInfoRepository,
                                                AccountRepository accountRepository) {
        this.medicalExaminationHistoryRepository = medicalExaminationHistoryRepository;
        this.patientInfoRepository = patientInfoRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<MedicalExaminationHistory> getMedicalExaminationHistoryList() {
        return medicalExaminationHistoryRepository.findAll();
    }

    @Override
    public boolean addNewMedicalExaminationHistory(MedicalExaminationHistoryDto medicalExaminationHistoryDto) {
        try {
            MedicalExaminationHistory medicalExaminationHistory = new MedicalExaminationHistory();
            medicalExaminationHistory.LoadFromDto(medicalExaminationHistoryDto);

            Account account = accountRepository.findById(medicalExaminationHistoryDto.getStaffId())
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            medicalExaminationHistory.setAccount(account);

            PatientInfo patientInfo = patientInfoRepository.findById(medicalExaminationHistoryDto.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));
            medicalExaminationHistory.setPatientInfo(patientInfo);

            medicalExaminationHistoryRepository.save(medicalExaminationHistory);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }
}
