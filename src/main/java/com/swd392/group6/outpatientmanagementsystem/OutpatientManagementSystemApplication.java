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

	@Bean
	public CommandLineRunner commandLineRunner(RoleRepository roleRepository,
											   AccountService accountService) {
		return runner -> {
			// insert default role
			Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
			if (roleAdmin == null) {
				Role role1 = new Role();
				role1.setName("ROLE_ADMIN");
				roleRepository.save(role1);
			}

			// insert default user
			AccountDto accountDto = new AccountDto();
			accountDto.setUsername("admin");
			accountDto.setPassword("admin");
			accountDto.setActive(true);

			if (accountService.findAccountByUsername(accountDto.getUsername()) == null){
				accountService.saveAdmin(accountDto);
			}
		};
	}
}