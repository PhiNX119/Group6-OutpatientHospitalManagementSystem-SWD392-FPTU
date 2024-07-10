package com.swd392.group6.outpatientmanagementsystem.controller;


import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicalExaminationHistoryDto;
import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicineDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Account;
import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalExaminationHistory;
import com.swd392.group6.outpatientmanagementsystem.repository.PatientInfoRepository;
import com.swd392.group6.outpatientmanagementsystem.service.MedicalExaminationHistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
        System.out.println(examinationList.size());
        MedicalExaminationHistoryDto[] examinationDtoList = new MedicalExaminationHistoryDto[examinationList.size()];
        for (int i = 0; i < examinationList.size(); i++) {
            examinationDtoList[i] = new MedicalExaminationHistoryDto();
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
        model.addAttribute("currStaff", getLoggedInAccount());
        model.addAttribute("dateNow", LocalDate.now() + "");
        return "medical-examination-history/add";
    }


    @PostMapping("/add")
    public String addNewCar(@Valid @ModelAttribute("medicalExaminationHistoryDto") MedicalExaminationHistoryDto medicalExaminationHistoryDto,
                            BindingResult result,
                            Model model) {
        System.out.println("===========----------- desc: " + medicalExaminationHistoryDto.getDescription());
        System.out.println("===========----------- date: " + medicalExaminationHistoryDto.getCreatedDate());
        System.out.println("===========----------- staff: " + medicalExaminationHistoryDto.getStaffId());
        System.out.println("===========----------- patient: " + medicalExaminationHistoryDto.getPatientId());
        System.out.println("===========----------- mrId: " + medicalExaminationHistoryDto.getMedicalRecordId());
        if (result.hasErrors()) {
            model.addAttribute("medicalExaminationHistoryDto", medicalExaminationHistoryDto);
            model.addAttribute("patientList", service.GetAllPatients());
            model.addAttribute("medicalRecordList", service.GetAllMedicalRecords());
            model.addAttribute("currStaff", getLoggedInAccount());
            model.addAttribute("dateNow", LocalDate.now() + "");

            return "medical-examination-history/add";
        } else {
            boolean isSuccess = service.AddNewMedicalExaminationHistory(medicalExaminationHistoryDto);
            if (isSuccess) {
                return "redirect:/medical-examination-history/list?addSuccess";
            } else {
                model.addAttribute("medicalExaminationHistoryDto", medicalExaminationHistoryDto);
                model.addAttribute("patientList", service.GetAllPatients());
                model.addAttribute("medicalRecordList", service.GetAllMedicalRecords());
                model.addAttribute("dateNow", LocalDate.now() + "");
                model.addAttribute("currStaff", getLoggedInAccount());
                model.addAttribute("addFailed", "Fail to add medical history examination.");
                return "medical-examination-history/add";
            }
        }
    }




    private Account getLoggedInAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Account) {
            return (Account) authentication.getPrincipal();
        }
        return null;
    }
}
