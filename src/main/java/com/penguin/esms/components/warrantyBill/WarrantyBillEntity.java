package com.penguin.esms.components.warrantyBill;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.penguin.esms.components.warrantyProduct.WarrantyProductEntity;
import com.penguin.esms.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
public class WarrantyBillEntity extends BaseEntity {
    private String staffId;
    private String customerId;
    private Date warrantyDate;
    @JsonIgnoreProperties(value = {"warrantyProducts"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warrantyBill")
    private List<WarrantyProductEntity> warrantyProducts;

}
