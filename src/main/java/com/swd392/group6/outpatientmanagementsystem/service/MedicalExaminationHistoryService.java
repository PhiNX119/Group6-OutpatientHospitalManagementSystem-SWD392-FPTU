package com.swd392.group6.outpatientmanagementsystem.service;

import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicalExaminationHistoryDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalExaminationHistoryService {
    List<MedicalExaminationHistory> getMedicalExaminationHistoryList();
    boolean addNewMedicalExaminationHistory(MedicalExaminationHistoryDto medicalExaminationHistoryDto);
}
