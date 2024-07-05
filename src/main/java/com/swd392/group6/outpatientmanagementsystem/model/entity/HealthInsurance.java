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
@Table(name = "health_insurance")
public class HealthInsurance implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String insuranceNumber;

    @Column(nullable = false)
    private Date dueDate;

    @Column(nullable = false)
    private boolean isValidation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_info_id")
    private PatientInfo patientInfo;
}
