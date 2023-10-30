package com.penguin.esms.components.supplier;

import com.penguin.esms.entity.BaseEntity;
import com.penguin.esms.entity.NoteEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class SupplierEntity extends NoteEntity {
    private String name;
    private String supplierId;
    private String staffId;


    private String phone;
    private String email;

    private String address;

    public SupplierEntity(String note, String name, String supplierId, String staffId, String phone, String email, String address) {
        super(note);
        this.name = name;
        this.supplierId = supplierId;
        this.staffId = staffId;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SupplierEntity{" +
                "name='" + name + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", staffId='" + staffId + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
