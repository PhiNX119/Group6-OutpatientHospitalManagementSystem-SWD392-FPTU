package com.swd392.group6.outpatientmanagementsystem.model.entity;

import com.swd392.group6.outpatientmanagementsystem.model.dto.AccountDto;
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
@Table(name = "account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    @Nationalized
    private String username;

    @Column(nullable = false)
    @Nationalized
    private String password;

    @Column
    @Nationalized
    private String name;

    @Column
    private Date dateOfBirth;

    @Column
    private boolean gender;

    @Column
    @Nationalized
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
    private Collection<HospitalFeePayment> hospitalFeePayments;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Collection<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private  Collection<MedicalExaminationHistory> medicalExaminationHistories;

    public void loadFromDto(AccountDto dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.isActive = dto.isActive();
    }
}