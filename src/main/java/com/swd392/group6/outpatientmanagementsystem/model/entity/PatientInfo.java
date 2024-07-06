package com.swd392.group6.outpatientmanagementsystem.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient_info")
public class PatientInfo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    @Nationalized
    private String name;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private boolean gender;

    @Column(nullable = false)
    @Nationalized
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne(mappedBy = "patientInfo", cascade = CascadeType.ALL)
    private HealthInsurance healthInsurance;

    @OneToMany(mappedBy = "patientInfo", cascade = CascadeType.ALL)
    private Collection<MedicalExaminationHistory> medicalExaminationHistories;
}
