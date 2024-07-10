package com.swd392.group6.outpatientmanagementsystem.controller;


import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicalExaminationHistoryDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Account;
import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalExaminationHistory;
import com.swd392.group6.outpatientmanagementsystem.service.AccountService;
import com.swd392.group6.outpatientmanagementsystem.service.MedicalExaminationHistoryService;
import com.swd392.group6.outpatientmanagementsystem.service.MedicalExaminationHistoryValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/medical-examination-history")
public class MedicalExaminationHistoryController {
    private final MedicalExaminationHistoryService service;
    private final MedicalExaminationHistoryValidationService validationService;


    @Autowired
    public MedicalExaminationHistoryController(MedicalExaminationHistoryService service,
                                               MedicalExaminationHistoryValidationService validationService) {
        this.service = service;
        this.validationService = validationService;
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
        medicalExaminationHistoryDto.setStaffId(service.GetLoggedInAccount().getAccount().getId());
        model.addAttribute("medicalExaminationHistoryDto", medicalExaminationHistoryDto);
        model.addAttribute("patientList", service.GetAllPatients());
        model.addAttribute("dateNow", LocalDate.now() + "");
        return "medical-examination-history/add";
    }


    @PostMapping("/add")
    public String addNewMedicalExaminationHistory(@Valid @ModelAttribute("medicalExaminationHistoryDto") MedicalExaminationHistoryDto medicalExaminationHistoryDto,
                            Model model) {
        String response = validationService.ValidateFields(medicalExaminationHistoryDto);
        String[] responses = response.split(" \\|\\| ");
        if (responses[0].equals("error")) {
            List<String> errors = new ArrayList<>();
            for (int i = 1; i < responses.length; i++) {
                errors.add(responses[i]);
            }
            model.addAttribute("medicalExaminationHistoryDto", medicalExaminationHistoryDto);
            model.addAttribute("patientList", service.GetAllPatients());
            model.addAttribute("currStaffId", service.GetLoggedInAccount().getAccount().getId());
            model.addAttribute("errors", errors);
            return "medical-examination-history/add";
        } else {
            boolean isSuccess = service.AddNewMedicalExaminationHistory(medicalExaminationHistoryDto);
            if (isSuccess) {
                model.addAttribute("response", responses[1]);
                model.addAttribute("patientList", service.GetAllPatients());
                return "medical-examination-history/add";
            } else {
                model.addAttribute("response", "Fail to add! Something went wrong!");
                model.addAttribute("medicalExaminationHistoryDto", medicalExaminationHistoryDto);
                model.addAttribute("patientList", service.GetAllPatients());
                model.addAttribute("currStaffId", service.GetLoggedInAccount().getAccount().getId());
                return "medical-examination-history/add";
            }
        }
    }

}
