package com.penguin.esms.components.warrantyBill;

import com.penguin.esms.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table
public class WarrantyBillEntity extends BaseEntity {
    private String staffId;
    private String customerId;

    private Date warrantyDate;

    public WarrantyBillEntity(String staffId, String customerId, Date warrantyDate) {
        this.staffId = staffId;
        this.customerId = customerId;
        this.warrantyDate = warrantyDate;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getWarrantyDate() {
        return warrantyDate;
    }

    public void setWarrantyDate(Date warrantyDate) {
        this.warrantyDate = warrantyDate;
    }

    @Override
    public String toString() {
        return "WarrantyBillEntity{" +
                "staffId='" + staffId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", warrantyDate=" + warrantyDate +
                '}';
    }
}
