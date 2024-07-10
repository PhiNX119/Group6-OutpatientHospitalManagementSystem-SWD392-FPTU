package com.swd392.group6.outpatientmanagementsystem.model.dto;

import com.swd392.group6.outpatientmanagementsystem.model.entity.Account;
import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalExaminationHistory;
import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalRecord;
import com.swd392.group6.outpatientmanagementsystem.model.entity.PatientInfo;
import com.swd392.group6.outpatientmanagementsystem.repository.PatientInfoRepository;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicalExaminationHistoryDto implements Serializable {
    @NotNull(message = "This field is required.")
    int id;
    String createdDate;
    @NotBlank(message = "This field is required.")
    String description;
    int staffId;
    int patientId;
    int medicalRecordId;

    PatientInfo patientInfo;
    MedicalRecord medicalRecord;
    Account account;

    public void loadFromEntity(MedicalExaminationHistory entity) {
        this.id = entity.getId();
        this.createdDate = entity.getCreateDate() + "";
        this.description = entity.getExaminationDescription();
        this.staffId = entity.getAccount().getId();
        this.patientId = entity.getPatientInfo().getId();
        this.medicalRecordId = entity.getMedicalRecord().getId();
    }
}
