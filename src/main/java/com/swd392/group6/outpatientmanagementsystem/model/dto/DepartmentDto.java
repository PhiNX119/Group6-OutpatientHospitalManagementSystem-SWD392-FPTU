package com.swd392.group6.outpatientmanagementsystem.model.dto;

import com.swd392.group6.outpatientmanagementsystem.model.entity.Department;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Medicine;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Department}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentDto implements Serializable {

    @NotBlank(message = "Name is mandatory")
    String name;

    boolean isActive;

    public void loadFromEntity(Department entity) {
        this.name = entity.getName();
        this.isActive = entity.isActive();
    }
}