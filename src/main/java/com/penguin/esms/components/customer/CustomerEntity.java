package com.penguin.esms.components.customer;

import com.penguin.esms.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class CustomerEntity extends BaseEntity {

    private String name;
    private String phone;
    private String address;

    public CustomerEntity(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public CustomerEntity(){}
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
