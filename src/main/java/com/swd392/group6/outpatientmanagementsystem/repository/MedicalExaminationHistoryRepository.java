package com.swd392.group6.outpatientmanagementsystem.repository;

import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalExaminationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalExaminationHistoryRepository extends JpaRepository<MedicalExaminationHistory, Integer> {
}