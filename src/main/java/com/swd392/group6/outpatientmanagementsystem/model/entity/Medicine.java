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
@Table(name = "medicine")
public class Medicine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    private Collection<MedicineItem> medicineItems;
}
