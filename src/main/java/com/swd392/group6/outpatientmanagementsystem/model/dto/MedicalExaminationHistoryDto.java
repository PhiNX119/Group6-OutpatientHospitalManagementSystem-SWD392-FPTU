package com.swd392.group6.outpatientmanagementsystem.model.dto;

import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalExaminationHistory;
import com.swd392.group6.outpatientmanagementsystem.model.entity.PatientInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "This field is required.")
    Date createdDate;
    @NotBlank(message = "This field is required.")
    String description;
    @NotNull(message = "This field is required.")
    int staffId;
    @NotNull(message = "This field is required.")
    int patientId;

    public void loadFromEntity(MedicalExaminationHistory entity) {
        this.id = entity.getId();
        this.createdDate = entity.getCreateDate();
        this.description = entity.getExaminationDescription();
        this.staffId = entity.getAccount().getId();
        this.patientId = entity.getPatientInfo().getId();
    }
}