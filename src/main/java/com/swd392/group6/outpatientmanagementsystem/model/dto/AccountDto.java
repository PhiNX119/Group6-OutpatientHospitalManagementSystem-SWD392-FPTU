package com.swd392.group6.outpatientmanagementsystem.model.dto;

import com.swd392.group6.outpatientmanagementsystem.model.entity.Account;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Account}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto implements Serializable {
    @NotBlank(message = "This field is required.")
    String username;

    @NotBlank(message = "This field is required.")
    String password;

    boolean isActive;

    public void loadFromEntity(Account entity) {
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.isActive = entity.isActive();
    }
}