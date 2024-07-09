package com.swd392.group6.outpatientmanagementsystem.config;

import com.swd392.group6.outpatientmanagementsystem.model.dto.AccountDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Account;
import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalRecord;
import com.swd392.group6.outpatientmanagementsystem.model.entity.PatientInfo;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Role;
import com.swd392.group6.outpatientmanagementsystem.repository.MedicalRecordRepository;
import com.swd392.group6.outpatientmanagementsystem.repository.PatientInfoRepository;
import com.swd392.group6.outpatientmanagementsystem.repository.RoleRepository;
import com.swd392.group6.outpatientmanagementsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class DataInitializer {

    private final RoleRepository roleRepository;

    private final AccountService accountService;
    private final PatientInfoRepository patientInfoRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public DataInitializer(RoleRepository roleRepository,
                           AccountService accountService,
                           PatientInfoRepository patientInfoRepository,
                           MedicalRecordRepository medicalRecordRepository) {
        this.roleRepository = roleRepository;
        this.accountService = accountService;
        this.patientInfoRepository = patientInfoRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @PostConstruct
    public void init() {
        createRoles();
        createRoleAccount("ROLE_ADMIN", "admin", "123");
        createRoleAccount("ROLE_DOCTOR", "doctor", "123");
        createRoleAccount("ROLE_CASHIER_COUNTER_STAFF", "cashier", "123");
        createRoleAccount("ROLE_RECEPTION_COUNTER_STAFF", "reception", "123");
        createRoleAccount("ROLE_PHARMACY_STAFF", "pharmacy", "123");

        createPatientsInfo();
        createMedicalRecords();
    }

    private void createRoles() {
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_DOCTOR");
        createRoleIfNotFound("ROLE_CASHIER_COUNTER_STAFF");
        createRoleIfNotFound("ROLE_RECEPTION_COUNTER_STAFF");
        createRoleIfNotFound("ROLE_PHARMACY_STAFF");
    }

    private void createPatientsInfo() {
        if (patientInfoRepository.findAll().isEmpty()) {
            createPatientInfo("Ha Noi", Date.valueOf("2003-06-04"), true, "Nguyen Quoc Toan", "0987654321");
            createPatientInfo("Hai Phong", Date.valueOf("2002-06-24"), false, "Nguyen Thai Hoc", "0987654453");
            createPatientInfo("Nam Dinh", Date.valueOf("2005-05-30"), true, "Tran Trung Kien", "0987652345");
        }
    }
    private void createMedicalRecords() {
        if (medicalRecordRepository.findAll().isEmpty()) {
            createMedicalRecord("None", "None", accountService.findAccountByUsername("doctor"));
            createMedicalRecord("None", "None", accountService.findAccountByUsername("doctor"));
            createMedicalRecord("None", "None", accountService.findAccountByUsername("doctor"));
        }
    }
    private void createRoleIfNotFound(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            Role newRole = new Role();
            newRole.setName(roleName);
            roleRepository.save(newRole);
        }
    }

    private void createRoleAccount(String roleName, String username, String password) {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername(username);
        accountDto.setPassword(password);
        accountDto.setActive(true);

        if (accountService.findAccountByUsername(accountDto.getUsername()) == null) {
            accountService.saveAccountWithRole(accountDto, roleName);
        }
    }

    private void createPatientInfo(String address, Date dob, boolean gender, String name, String phone) {
        PatientInfo patientInfo = new PatientInfo();
        patientInfo.setAddress(address);
        patientInfo.setDateOfBirth(dob);
        patientInfo.setGender(gender);
        patientInfo.setName(name);
        patientInfo.setPhoneNumber(phone);

        patientInfoRepository.save(patientInfo);
    }

    private void createMedicalRecord(String description, String advise, Account account) {
        if (account.getRole().getId() == 2) {

            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setDescription(description);
            medicalRecord.setDoctorAdvise(advise);
            medicalRecord.setAccount(account);

            medicalRecordRepository.save(medicalRecord);
        }
    }
}
