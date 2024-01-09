package com.penguin.esms.components.product.dto;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.components.supplier.dto.SupplierDTO;
import com.penguin.esms.utils.Random;
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
    private String id;
    @NotNull(message = "name is required")
    private String name;
    private String categoryId;
    private String unit;

    private Long price;
    private Integer quantity = 0;
    private Integer warrantyPeriod;
    private Boolean isAvailable;
    private String photoURL;
    private List<String> suppliers = new ArrayList<>();

    public ProductDTO(String id , String name, String categoryId, String unit, Long price, Integer quantity, Integer warrantyPeriod, Boolean isAvailable, String photoURL){
        this.setId(id);
        this.name = name;
        this.categoryId = categoryId;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.warrantyPeriod = warrantyPeriod;
        this.isAvailable = isAvailable;
        this.photoURL = photoURL;
    }

    public ProductDTO(String name, String categoryId, String unit, Long price, Integer quantity, Integer warrantyPeriod, Boolean isAvailable, String photoURL){
        this.name = name;
        this.categoryId = categoryId;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.warrantyPeriod = warrantyPeriod;
        this.isAvailable = isAvailable;
        this.photoURL = photoURL;
    }


}
