package com.swd392.group6.outpatientmanagementsystem.controller;


import com.swd392.group6.outpatientmanagementsystem.model.entity.Medicine;
import com.swd392.group6.outpatientmanagementsystem.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/medical-history-examination")
public class MedicalHistoryExaminationController {


    private final AccountService accountService;


    @Autowired
    public MedicalHistoryExaminationController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/list")
    public String showAll(Model model) {
        return "medical-history-examination/list";
    }
}
