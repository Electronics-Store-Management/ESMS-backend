package com.penguin.esms.components.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table
public class ProductEntity {
    private String name;
    private Long price;
    private Integer quantity;
    private Date warrantyPeriod;
    private Boolean isAvailable;

    public ProductEntity(String name, Long price, Integer quantity, Date warrantyPeriod, Boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.warrantyPeriod = warrantyPeriod;
        this.isAvailable = isAvailable;
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
                ", price=" + price +
                ", quantity=" + quantity +
                ", warrantyPeriod=" + warrantyPeriod +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
