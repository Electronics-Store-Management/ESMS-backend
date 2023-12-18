package com.penguin.esms.components.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.saleBill.SaleBillEntity;
import com.penguin.esms.components.staff.validators.PhoneNumberFormat;
import com.penguin.esms.components.warrantyBill.WarrantyBillEntity;
import com.penguin.esms.components.warrantyProduct.WarrantyProductEntity;
import com.penguin.esms.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@Audited
public class CustomerEntity extends BaseEntity {
    private String name;
    @Column(unique=true)
    @PhoneNumberFormat(message = "Invalid phone number")
    private String phone;
    private String address;
    //sai ne
    @JsonIgnoreProperties(value = {"warrantyBill"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
//    @NotAudited
    @JsonIgnore
    private List<WarrantyBillEntity> warrantyBills;

    @JsonIgnoreProperties(value = {"saleBill"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
//    @NotAudited
    @JsonIgnore
    private List<SaleBillEntity> saleBills;
}
