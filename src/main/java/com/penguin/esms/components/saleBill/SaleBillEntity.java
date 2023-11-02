package com.penguin.esms.components.saleBill;

import com.penguin.esms.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table
public class SaleBillEntity extends NoteEntity {
    private String staffId;
    private String customerId;

    private String paymentMethod;
    private Date saleDate;
    private Float discount;

    public SaleBillEntity(String note, String staffId, String customerId, String paymentMethod, Date saleDate, Float discount) {
        super(note);
        this.staffId = staffId;
        this.customerId = customerId;
        this.paymentMethod = paymentMethod;
        this.saleDate = saleDate;
        this.discount = discount;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "SaleBillEntity{" +
                "staffId='" + staffId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", saleDate=" + saleDate +
                ", discount=" + discount +
                '}';
    }
}
