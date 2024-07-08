package com.swd392.group6.outpatientmanagementsystem.repository;

import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalExaminationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalExaminationHistoryRepository extends JpaRepository<MedicalExaminationHistory, Integer> {

}
