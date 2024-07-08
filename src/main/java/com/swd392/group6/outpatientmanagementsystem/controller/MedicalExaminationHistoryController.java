package com.swd392.group6.outpatientmanagementsystem.controller;


import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicalExaminationHistoryDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.MedicalExaminationHistory;
import com.swd392.group6.outpatientmanagementsystem.service.MedicalExaminationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
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
}
