package com.swd392.group6.outpatientmanagementsystem.service;

import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicalExaminationHistoryDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalExaminationHistory;
import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalRecord;
import com.swd392.group6.outpatientmanagementsystem.model.entity.PatientInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalExaminationHistoryService {
    List<MedicalExaminationHistory> GetAll();
    List<PatientInfo> GetAllPatients();
    boolean AddNewMedicalExaminationHistory(MedicalExaminationHistoryDto mehDto);

    List<MedicalRecord> GetAllMedicalRecords();
}
