package com.penguin.esms.components.product;

import com.penguin.esms.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table
public class ProductEntity extends BaseEntity {
    private String name;
    private String categoryId;
    private String supplierId;
    private String unit;
    private Long price;
    private Integer quantity;
    private Date warrantyPeriod;
    private Boolean isAvailable;

    public ProductEntity(String name, String categoryId, String supplierId, String unit, Long price, Integer quantity, Date warrantyPeriod, Boolean isAvailable) {
        this.name = name;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.warrantyPeriod = warrantyPeriod;
        this.isAvailable = isAvailable;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(Date warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "name='" + name + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", warrantyPeriod=" + warrantyPeriod +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
