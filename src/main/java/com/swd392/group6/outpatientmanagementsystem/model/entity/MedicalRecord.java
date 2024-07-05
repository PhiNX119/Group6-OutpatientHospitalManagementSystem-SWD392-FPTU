package com.swd392.group6.outpatientmanagementsystem.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medical_record")
public class MedicalRecord implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String doctorAdvise;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Account account;

    @OneToOne(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    private HospitalFeePayment hospitalFeePayment;

    @OneToOne(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    private MedicineInvoice medicineInvoice;

    @OneToOne(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    private ServiceInvoice serviceInvoice;

    @OneToOne(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    private MedicalExaminationHistory medicalExaminationHistory;
}
