package com.swd392.group6.outpatientmanagementsystem.service.impl;

import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicalExaminationHistoryDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Account;
import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalExaminationHistory;
import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalRecord;
import com.swd392.group6.outpatientmanagementsystem.model.entity.PatientInfo;
import com.swd392.group6.outpatientmanagementsystem.repository.AccountRepository;
import com.swd392.group6.outpatientmanagementsystem.repository.MedicalExaminationHistoryRepository;
import com.swd392.group6.outpatientmanagementsystem.repository.MedicalRecordRepository;
import com.swd392.group6.outpatientmanagementsystem.repository.PatientInfoRepository;
import com.swd392.group6.outpatientmanagementsystem.service.MedicalExaminationHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalExaminationHistoryServiceImpl implements MedicalExaminationHistoryService {

    private final MedicalExaminationHistoryRepository repo;
    private final PatientInfoRepository patientRepo;
    private final MedicalRecordRepository medicalRecordRepo;
    private final AccountRepository accountRepository;

    public MedicalExaminationHistoryServiceImpl(MedicalExaminationHistoryRepository repo,
                                                PatientInfoRepository patientRepo,
                                                MedicalRecordRepository medicalRecordRepo,
                                                AccountRepository accountRepository) {
        this.repo = repo;
        this.patientRepo = patientRepo;
        this.medicalRecordRepo = medicalRecordRepo;
        this.accountRepository = accountRepository;
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
            meh.setAccount(accountRepository.findById(mehDto.getStaffId()).get());
            meh.setMedicalRecord(medicalRecordRepo.findById(mehDto.getMedicalRecordId()).get());
            meh.setPatientInfo(patientRepo.findById(mehDto.getPatientId()).get());
            repo.save(meh);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public List<MedicalRecord> GetAllMedicalRecords() {
        return medicalRecordRepo.findAll();
    }
}
