package com.swd392.group6.outpatientmanagementsystem.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fee_payment")
public class FeePayment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private float totalFee;

    @Column(nullable = false)
    private Date dueDate;

    @Column(nullable = false)
    private boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "cashier_counter_staff_id")
    private Account account;

    @OneToMany(mappedBy = "feePayment", cascade = CascadeType.ALL)
    private Collection<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "feePayment", cascade = CascadeType.ALL)
    private Collection<PatientInfo> patientInfos;
}
