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
@Table(name = "account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String accountName;

    @Column
    private Date dateOfBirth;

    @Column
    private boolean gender;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @Column(nullable = false)
    private boolean isActive;

    @Column
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="account_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    private Collection<Role> roles;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Collection<FeePayment> feePayments;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Collection<MedicinePrescription> medicinePrescriptions;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private  Collection<MedicalExaminationHistory> medicalExaminationHistories;
}