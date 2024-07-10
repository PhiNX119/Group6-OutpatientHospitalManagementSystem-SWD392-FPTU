package com.swd392.group6.outpatientmanagementsystem.repository;

import com.swd392.group6.outpatientmanagementsystem.model.entity.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientInfoRepository extends JpaRepository<PatientInfo, Integer> {
}