package com.swd392.group6.outpatientmanagementsystem;

import com.swd392.group6.outpatientmanagementsystem.model.dto.AccountDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Role;
import com.swd392.group6.outpatientmanagementsystem.repository.RoleRepository;
import com.swd392.group6.outpatientmanagementsystem.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OutpatientManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutpatientManagementSystemApplication.class, args);
	}
}