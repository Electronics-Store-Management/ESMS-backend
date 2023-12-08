package com.penguin.esms.components.warrantyBill;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.penguin.esms.components.customer.CustomerEntity;
import com.penguin.esms.components.warrantyProduct.WarrantyProductEntity;
import com.penguin.esms.entity.BaseEntity;
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
public class WarrantyBillEntity extends BaseEntity {
    private String staffId;
//    private String customerId;
    private Date warrantyDate;
    @NotAudited
    @JsonIgnoreProperties(value = {"warrantyProducts"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warrantyBill")
    private List<WarrantyProductEntity> warrantyProducts;
    @NotAudited
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties(value = {"warrantyBills"})
    private CustomerEntity customer;

}
