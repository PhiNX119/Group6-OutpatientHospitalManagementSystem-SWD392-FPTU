package com.swd392.group6.outpatientmanagementsystem.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String examinationDescription;

    @Column(nullable = false)
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "reception_counter_staff_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientInfo patientInfo;

    @OneToOne(mappedBy = "medicalExaminationHistory", cascade = CascadeType.ALL)
    private MedicalRecord medicalRecord;
}
