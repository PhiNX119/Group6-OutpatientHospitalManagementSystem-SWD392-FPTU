package com.swd392.group6.outpatientmanagementsystem.service;

import com.swd392.group6.outpatientmanagementsystem.model.dto.PatientInfoDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.PatientInfo;

import java.util.List;

public interface PatientInfoService {
    PatientInfo getPatientInfoById(Integer id);
    List<PatientInfo> getPatientInfoList();
    PatientInfo addNewPatientInfo(PatientInfoDto patientInfo);
    PatientInfo updatePatientInfo(PatientInfoDto patientInfo);
    boolean deletePatientInfo(Integer id);
}
