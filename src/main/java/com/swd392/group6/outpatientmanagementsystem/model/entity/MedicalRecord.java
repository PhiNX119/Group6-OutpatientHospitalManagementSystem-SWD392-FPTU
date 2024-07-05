package com.swd392.group6.outpatientmanagementsystem.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

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
    private String medicalDescription;

    @Column(nullable = false)
    private String doctorAdvise;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "fee_payment_id")
    private FeePayment feePayment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_examination_history_id")
    private MedicalExaminationHistory medicalExaminationHistory;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    private Collection<MedicineInvoice> medicineInvoices;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    private Collection<ServiceInvoice> serviceInvoices;
}
