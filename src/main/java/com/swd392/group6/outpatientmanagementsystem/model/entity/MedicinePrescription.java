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
@Table(name = "medicine_prescription")
public class MedicinePrescription implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Account account;

    @OneToMany(mappedBy = "medicinePrescription", cascade = CascadeType.ALL)
    private Collection<MedicineItem> medicineItems;

    @ManyToOne
    @JoinColumn(name = "medicine_invoice_id")
    private MedicineInvoice medicineInvoice;
}
