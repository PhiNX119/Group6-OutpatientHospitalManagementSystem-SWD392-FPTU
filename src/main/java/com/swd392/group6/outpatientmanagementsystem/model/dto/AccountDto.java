package com.swd392.group6.outpatientmanagementsystem.model.dto;

import com.swd392.group6.outpatientmanagementsystem.model.entity.Account;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Department;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {@link Account}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto implements Serializable {
    int id;

    @NotBlank(message = "This field is required.")
    String username;

    @NotBlank(message = "This field is required.")
    String password;

    @NotBlank(message = "This field is required.")
    String name;

    @NotNull(message = "This field is required.")
            @PastOrPresent(message = "This field must from past or present")
    Date dateOfBirth;


    boolean gender;

    @NotBlank(message = "This field is required.")
    String address;

    @NotBlank(message = "This field is required.")
    String phoneNumber;

    @NotBlank(message = "This field is required.")
    String roleName;

    @NotBlank(message = "This field is required.")
    String departmentName;


    boolean isActive;

    public void loadFromEntity(Account entity) {
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.name = entity.getName();
        this.dateOfBirth = entity.getDateOfBirth();
        this.gender = entity.isGender();
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
        this.roleName = entity.getRole().getName();
        this.departmentName = entity.getDepartment().getName();
        this.isActive = entity.isActive();
    }
}