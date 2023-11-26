package com.penguin.esms.components.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotNull(message = "name is required")
    private String name;
    private String categoryId;
    private List<String> suppliers = new ArrayList<>();
    private String unit;
    private Long price;
    private Integer quantity = 0;
    private Integer warrantyPeriod;
    private Boolean isAvailable;
    private String photoURL;
}
