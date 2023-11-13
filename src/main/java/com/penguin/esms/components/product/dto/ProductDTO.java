package com.penguin.esms.components.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotNull(message = "name is required")
    private String name;
    private String categoryId;
    private String supplierId;
    private String unit;
    private Long price;
    private Integer quantity;
    private Integer warrantyPeriod;
    private Boolean isAvailable;
    private String photoURL;
}
