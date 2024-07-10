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
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @Min(value = 0, message = "Quantity must be zero or greater")
    private int quantity;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private double price;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    private boolean isActive;

    public void loadFromEntity(Medicine entity) {
        this.name = entity.getName();
        this.quantity = entity.getQuantity();
        this.price = entity.getQuantity();
        this.description = entity.getDescription();
        this.isActive = entity.isActive();
    }
}