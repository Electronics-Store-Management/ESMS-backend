package com.penguin.esms.components.saleBill;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.penguin.esms.components.customer.CustomerEntity;
import com.penguin.esms.components.importProduct.ImportProductEntity;
import com.penguin.esms.components.saleProduct.SaleProductEntity;
import com.penguin.esms.entity.NoteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@Audited
public class SaleBillEntity extends NoteEntity {
    private String staffId;
    private String paymentMethod;
    private Float discount;
    @JsonIgnoreProperties(value = {"saleBill"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "saleBill")
    private List<SaleProductEntity> saleProducts;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties(value = {"saleBills"})
    private CustomerEntity customer;

    public SaleBillEntity(String staffId, String paymentMethod, Float discount) {
        this.staffId = staffId;
        this.paymentMethod = paymentMethod;
        this.discount =discount;
    }
    public SaleBillEntity(String staffId, CustomerEntity customer, String paymentMethod, Float discount) {
        this.staffId = staffId;
        this.setCustomer(customer);
        this.paymentMethod = paymentMethod;
        this.discount = discount;
    }
    public SaleBillEntity(String staffId, CustomerEntity customer, String paymentMethod, Float discount,String id ) {
        this.staffId = staffId;
        this.setCustomer(customer);
        this.paymentMethod = paymentMethod;
        this.discount = discount;
        this.setId(id);
    }
}
