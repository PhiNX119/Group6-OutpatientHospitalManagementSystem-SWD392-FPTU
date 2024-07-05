package com.swd392.group6.outpatientmanagementsystem.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String patientName;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private boolean gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "fee_payment_id")
    private FeePayment feePayment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "health_insurance_id")
    private HealthInsurance healthInsurance;

    @OneToMany(mappedBy = "patientInfo", cascade = CascadeType.ALL)
    private Collection<MedicalExaminationHistory> medicalExaminationHistories;
}
