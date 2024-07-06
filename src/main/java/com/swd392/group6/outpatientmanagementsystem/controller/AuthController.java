package com.swd392.group6.outpatientmanagementsystem.controller;

import com.swd392.group6.outpatientmanagementsystem.model.dto.AccountDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.CustomUserDetails;
import com.swd392.group6.outpatientmanagementsystem.service.AccountService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;

/**
 * @author phinx
 * @description controller class contain authorization function
 */
@Controller
public class AuthController {
    private final AccountService accountService;
    private final HttpSession session;

    @Autowired
    public AuthController(AccountService accountService,
                          HttpSession session) {
        this.accountService = accountService;
        this.session = session;
    }

    /**
     * @author phinx
     * @description redirect to home page for each actor
     */
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    /**
     * @author phinx
     * @description redirect to home page for each actor
     */
    @GetMapping(value = {"/", "/home"})
    public String homePage(Model model) {
        return "home-page";
    }

    /**
     * @author phinx
     * @description show error 403 page
     */
    @GetMapping("/error-403")
    public String showError403() {
        return "error/403";
    }
}
