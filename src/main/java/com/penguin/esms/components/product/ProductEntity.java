package com.penguin.esms.components.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity extends BaseEntity {
    @Column(unique = true)
    @NotNull
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties(value = {"products"})
    private CategoryEntity category;
    private String supplierId;
    private String unit;
    private Long price;
    private Integer quantity;
    private Integer warrantyPeriod;
    private Boolean isAvailable = true;
    private String photoURL;

    @Override
    public String toString() {
        return "ProductEntity{" +
                "name='" + name + '\'' +
                (", category='" + (category != null ? category.getName() : "None")) + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", warrantyPeriod=" + warrantyPeriod +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
