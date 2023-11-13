package com.penguin.esms.components.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    private String name;
    private String categoryId;
    private String supplierId;
    private String unit;
    private Long price;
    private Integer quantity;
    private Integer warrantyPeriod;
    private Boolean isAvailable;
    private File photo;
}
