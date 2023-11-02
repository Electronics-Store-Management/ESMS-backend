package com.penguin.esms.components.importBill;

import com.penguin.esms.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table
public class ImportBillEntity extends NoteEntity {
    private String staffId;
    private String supplierId;

    private String paymentMethod;
    private Date importDate;

    public ImportBillEntity(String note, String staffId, String supplierId, String paymentMethod, Date importDate) {
        super(note);
        this.staffId = staffId;
        this.supplierId = supplierId;
        this.paymentMethod = paymentMethod;
        this.importDate = importDate;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    @Override
    public String toString() {
        return "ImportBillEntity{" +
                "staffId='" + staffId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", importDate=" + importDate +
                '}';
    }
}
