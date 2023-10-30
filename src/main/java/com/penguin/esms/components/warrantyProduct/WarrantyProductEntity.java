package com.penguin.esms.components.warrantyProduct;

import com.penguin.esms.entity.NoteEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Date;
@Entity
@Table
public class WarrantyProductEntity extends NoteEntity {
    private String staffId;
    private String warrantyBillId;

    private String productId;
    private Integer quantity;
    private String warrantyContent;

    public WarrantyProductEntity(String note, String warrantyBillId,String staffId, String productId, Integer quantity, String warrantyContent) {
        super(note);
        this.warrantyBillId = warrantyBillId;
        this.staffId = staffId;
        this.productId = productId;
        this.quantity = quantity;
        this.warrantyContent = warrantyContent;
    }

    public String getWarrantyBillId() {
        return warrantyBillId;
    }

    public void setWarrantyBillId(String warrantyBillId) {
        this.warrantyBillId = warrantyBillId;
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
                ", warrantyBillId='" + warrantyBillId + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", warrantyContent='" + warrantyContent + '\'' +
                '}';
    }
}
