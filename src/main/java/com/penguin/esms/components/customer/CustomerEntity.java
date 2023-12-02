package com.penguin.esms.components.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.saleBill.SaleBillEntity;
import com.penguin.esms.components.warrantyBill.WarrantyBillEntity;
import com.penguin.esms.components.warrantyProduct.WarrantyProductEntity;
import com.penguin.esms.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
public class CustomerEntity extends BaseEntity {
    private String name;
    private String phone;
    private String address;
    //sai ne
    @JsonIgnoreProperties(value = {"warrantyBill"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<WarrantyBillEntity> warrantyBills;

    @JsonIgnoreProperties(value = {"saleBill"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<SaleBillEntity> saleBills;
}
