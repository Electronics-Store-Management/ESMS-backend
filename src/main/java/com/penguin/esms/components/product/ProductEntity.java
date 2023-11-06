package com.penguin.esms.components.product;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity extends BaseEntity {
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    private String supplierId;
    private String unit;
    private Long price;
    private Integer quantity;
    private Date warrantyPeriod;
    private Boolean isAvailable;

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
