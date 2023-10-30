package com.penguin.esms.components.importProduct;

import com.penguin.esms.entity.BaseEntity;
import com.penguin.esms.entity.NoteEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class ImportProductEntity extends BaseEntity {
    private String productId;
    private String importBillId;

    private Integer quantity;
    private Integer index;
    private Long price;
    private String unit;

    public ImportProductEntity(String productId,String importBillId, Integer quantity, Integer index, Long price, String unit) {
        this.productId = productId;
        this.importBillId = importBillId;
        this.quantity = quantity;
        this.index = index;
        this.price = price;
        this.unit = unit;
    }

    public String getImportBillId() {
        return importBillId;
    }

    public void setImportBillId(String importBillId) {
        this.importBillId = importBillId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
        return "ImportProductEntity{" +
                "productId='" + productId + '\'' +
                ", importBillId='" + importBillId + '\'' +
                ", quantity=" + quantity +
                ", index=" + index +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                '}';
    }
}
