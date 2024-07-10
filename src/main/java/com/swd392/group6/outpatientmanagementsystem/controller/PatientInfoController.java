package com.swd392.group6.outpatientmanagementsystem.controller;

import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicineDto;
import com.swd392.group6.outpatientmanagementsystem.model.dto.PatientInfoDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.PatientInfo;
import com.swd392.group6.outpatientmanagementsystem.service.PatientInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patientInfo")
public class PatientInfoController {
    private final PatientInfoService patientInfoService;

    @Autowired
    public PatientInfoController(PatientInfoService patientInfoService) {
        this.patientInfoService = patientInfoService;
    }

    @GetMapping("/list")
    public String showPatientInfoList(Model model) {
        List<PatientInfo> patientInfoList = patientInfoService.findAll();
        model.addAttribute("patientInfoList", patientInfoList);
        return "patientInfo/list";
    }

    @GetMapping("/detail")
    public String viewPatientInfoDetail(@RequestParam("id") Integer patientInfoId, Model model) {
        PatientInfo patientInfo = patientInfoService.getPatientInfoById(patientInfoId);
        PatientInfoDto patientInfoDto = new PatientInfoDto();
        patientInfoDto.loadFromEntity(patientInfo);
        model.addAttribute("patientInfo", patientInfo);
        return "patientInfo/detail";
    }

    @GetMapping("/add")
    public String getAddNewPatientInfo(Model model) {
        PatientInfoDto patientInfoDto = new PatientInfoDto();
        model.addAttribute("patientInfoDto", patientInfoDto);
        return "patientInfo/add";
    }

    @PostMapping("/add")
    public String addNewPatientInfo(@Valid @ModelAttribute("patientInfoDto") PatientInfoDto patientInfoDto,
                                    BindingResult result,
                                    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("patientInfoDto", patientInfoDto);
            return "patientInfo/add";
        } else {
            patientInfoService.addNewPatientInfo(patientInfoDto);
            return "redirect:/patientInfo/add?addSuccess=true";
        }
    }
}
