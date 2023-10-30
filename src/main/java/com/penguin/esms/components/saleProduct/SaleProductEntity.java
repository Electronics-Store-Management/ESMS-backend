package com.penguin.esms.components.saleProduct;

import com.penguin.esms.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class SaleProductEntity extends BaseEntity {
    private String productId;
    private String saleBillId;
    private Integer quantity;
    private Integer index;
    private Long price;
    private String unit;

    public SaleProductEntity(String productId, String saleBillId, Integer quantity, Integer index, Long price, String unit) {
        this.productId = productId;
        this.saleBillId = saleBillId;
        this.quantity = quantity;
        this.index = index;
        this.price = price;
        this.unit = unit;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSaleBillId() {
        return saleBillId;
    }

    public void setSaleBillId(String saleBillId) {
        this.saleBillId = saleBillId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "SaleProductEntity{" +
                "productId='" + productId + '\'' +
                ", saleBillId='" + saleBillId + '\'' +
                ", quantity=" + quantity +
                ", index=" + index +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                '}';
    }
}
