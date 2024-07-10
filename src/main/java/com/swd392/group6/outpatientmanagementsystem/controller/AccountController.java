package com.swd392.group6.outpatientmanagementsystem.controller;

import com.swd392.group6.outpatientmanagementsystem.model.dto.AccountDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Account;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Department;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Role;
import com.swd392.group6.outpatientmanagementsystem.service.AccountService;
import com.swd392.group6.outpatientmanagementsystem.service.DepartmentService;
import com.swd392.group6.outpatientmanagementsystem.service.impl.RoleServiceImpl;
import com.swd392.group6.outpatientmanagementsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final DepartmentService departmentService;
    private final RoleService roleService;

    @Autowired
    public AccountController(AccountService accountService, DepartmentService departmentService, RoleService roleService) { this.accountService = accountService;
        this.departmentService = departmentService;
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public String ShowAccountList(Model model) {
        List<Account> accountList = accountService.findAll();
        model.addAttribute("accountList", accountList);

        return "account/list";
    }

    @GetMapping("/detail")
    public String viewAccountDetail(@RequestParam("id") Integer accountId, Model model) {
        Account account = accountService.findAccountById(accountId);
        AccountDto accountDto = new AccountDto();
        accountDto.loadFromEntity(account);
        model.addAttribute("accountDto", accountDto);

        return "account/detail";
    }

    @GetMapping("/add")
    public String addNewAccount(Model model) {
        AccountDto accountDto = new AccountDto();
        List<Department> departmentList = departmentService.findAllDepartments();
        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("accountDto", accountDto);

        return "account/add";
    }

    @PostMapping("/add")
    public String addNewAccount(@Valid @ModelAttribute("accountDto") AccountDto accountDto,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("accountDto", accountDto);
            return "account/add";
        } else {
            accountDto.setActive(true);
            accountService.addNewAccount(accountDto);
            return "redirect:/account/list?addSuccess";
        }
    }
}
