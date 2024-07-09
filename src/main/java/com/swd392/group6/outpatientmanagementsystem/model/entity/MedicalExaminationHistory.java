package com.swd392.group6.outpatientmanagementsystem.model.entity;

import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicalExaminationHistoryDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medical_examination_history")
public class MedicalExaminationHistory implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    @Nationalized
    private String examinationDescription;

    @Column(nullable = false)
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "reception_counter_staff_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientInfo patientInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    public void LoadFromDto(MedicalExaminationHistoryDto mehDto) {
        this.examinationDescription = mehDto.getDescription();
        this.patientInfo = mehDto.getPatientInfo();
        this.account = mehDto.getAccount();
        this.createDate = Date.valueOf(String.valueOf(mehDto.getCreatedDate()));
        this.medicalRecord = mehDto.getMedicalRecord();
    }
}
