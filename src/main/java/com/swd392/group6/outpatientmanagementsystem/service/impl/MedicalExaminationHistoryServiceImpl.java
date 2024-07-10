package com.swd392.group6.outpatientmanagementsystem.service.impl;

import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicalExaminationHistoryDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.*;
import com.swd392.group6.outpatientmanagementsystem.repository.AccountRepository;
import com.swd392.group6.outpatientmanagementsystem.repository.MedicalExaminationHistoryRepository;
import com.swd392.group6.outpatientmanagementsystem.repository.MedicalRecordRepository;
import com.swd392.group6.outpatientmanagementsystem.repository.PatientInfoRepository;
import com.swd392.group6.outpatientmanagementsystem.service.AccountService;
import com.swd392.group6.outpatientmanagementsystem.service.MedicalExaminationHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalExaminationHistoryServiceImpl implements MedicalExaminationHistoryService {

    private final MedicalExaminationHistoryRepository repo;
    private final PatientInfoRepository patientRepo;
    private final MedicalRecordRepository medicalRecordRepo;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public MedicalExaminationHistoryServiceImpl(MedicalExaminationHistoryRepository repo,
                                                PatientInfoRepository patientRepo,
                                                MedicalRecordRepository medicalRecordRepo,
                                                AccountRepository accountRepository,
                                                AccountService accountService) {
        this.repo = repo;
        this.patientRepo = patientRepo;
        this.medicalRecordRepo = medicalRecordRepo;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }


    @Override
    public List<MedicalExaminationHistory> GetAll() {
        return repo.findAll();
    }

    @Override
    public List<PatientInfo> GetAllPatients() {
        return patientRepo.findAll();
    }

    @Override
    public boolean AddNewMedicalExaminationHistory(MedicalExaminationHistoryDto mehDto) {
        try {
            MedicalExaminationHistory meh = new MedicalExaminationHistory();
            meh.LoadFromDto(mehDto);

            Account account = accountRepository.findById(mehDto.getStaffId())
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            meh.setAccount(account);

            PatientInfo patientInfo = patientRepo.findById(mehDto.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));
            meh.setPatientInfo(patientInfo);

            repo.save(meh);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<MedicalRecord> GetAllMedicalRecords() {
        return medicalRecordRepo.findAll();
    }

    @Override
    public CustomUserDetails GetLoggedInAccount() {
        return accountService.getUserDetail();
    }
}
