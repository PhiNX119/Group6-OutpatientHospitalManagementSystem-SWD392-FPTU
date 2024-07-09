package com.swd392.group6.outpatientmanagementsystem.controller;


import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicalExaminationHistoryDto;
import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicineDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalExaminationHistory;
import com.swd392.group6.outpatientmanagementsystem.repository.PatientInfoRepository;
import com.swd392.group6.outpatientmanagementsystem.service.MedicalExaminationHistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/medical-examination-history")
public class MedicalExaminationHistoryController {
    private final MedicalExaminationHistoryService service;


    @Autowired
    public MedicalExaminationHistoryController(MedicalExaminationHistoryService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String showAll(Model model) {
        List<MedicalExaminationHistory> examinationList = service.GetAll();
        MedicalExaminationHistoryDto[] examinationDtoList = new MedicalExaminationHistoryDto[examinationList.size()];
        for (int i = 0; i < examinationList.size(); i++) {
            examinationDtoList[i].loadFromEntity(examinationList.get(i));
        }
        model.addAttribute("examinationList", examinationDtoList);
        return "medical-examination-history/list";
    }


    @GetMapping("/add")
    public String addNewMedicalExaminationHistory(Model model) {
        MedicalExaminationHistoryDto medicalExaminationHistoryDto = new MedicalExaminationHistoryDto();
        model.addAttribute("medicalExaminationHistoryDto", medicalExaminationHistoryDto);
        model.addAttribute("patientList", service.GetAllPatients());
        model.addAttribute("medicalRecordList", service.GetAllMedicalRecords());
        return "medical-examination-history/add";
    }


    @PostMapping("/add")
    public String addNewCar(@Valid @ModelAttribute("medicalExaminationHistoryDto") MedicalExaminationHistoryDto medicalExaminationHistoryDto,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("medicalExaminationHistoryDto", medicalExaminationHistoryDto);
            model.addAttribute("patientList", service.GetAllPatients());
            model.addAttribute("medicalRecordList", service.GetAllMedicalRecords());
            return "medical-examination-history/add";
        } else {

            return "redirect:/medical-examination-history/list?addSuccess";
        }
    }
}
