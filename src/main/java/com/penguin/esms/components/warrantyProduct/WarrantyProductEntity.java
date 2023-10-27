package com.penguin.esms.components.warrantyProduct;

import com.penguin.esms.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;
@Entity
@Table
public class WarrantyProductEntity extends NoteEntity {
    private String staffId;
    private String productId;
    private Integer quantity;
    private String warrantyContent;

    public WarrantyProductEntity(String note, String staffId, String productId, Integer quantity, String warrantyContent) {
        super(note);
        this.staffId = staffId;
        this.productId = productId;
        this.quantity = quantity;
        this.warrantyContent = warrantyContent;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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

    public String getWarrantyContent() {
        return warrantyContent;
    }

    public void setWarrantyContent(String warrantyContent) {
        this.warrantyContent = warrantyContent;
    }

    @Override
    public String toString() {
        return "WarrantyProductEntity{" +
                "staffId='" + staffId + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", warrantyContent='" + warrantyContent + '\'' +
                '}';
    }
}
