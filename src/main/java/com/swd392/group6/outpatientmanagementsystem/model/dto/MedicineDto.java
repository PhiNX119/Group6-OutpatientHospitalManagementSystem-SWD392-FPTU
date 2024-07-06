package com.swd392.group6.outpatientmanagementsystem.model.dto;

import com.swd392.group6.outpatientmanagementsystem.model.entity.Medicine;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Medicine}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicineDto implements Serializable {
    @NotBlank(message = "This field is required.")
    String name;

    @NotNull(message = "This field is required.")
    int quantity;

    @NotNull(message = "This field is required.")
    double price;

    @NotBlank(message = "This field is required.")
    String description;

    boolean isActive;

    public void loadFromEntity(Medicine entity) {
        this.name = entity.getName();
        this.quantity = entity.getQuantity();
        this.price = entity.getQuantity();
        this.description = entity.getDescription();
        this.isActive = entity.isActive();
    }
}