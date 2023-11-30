package com.penguin.esms.components.saleBill;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.penguin.esms.components.importProduct.ImportProductEntity;
import com.penguin.esms.components.saleProduct.SaleProductEntity;
import com.penguin.esms.entity.NoteEntity;
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
public class SaleBillEntity extends NoteEntity {
    private String staffId;
    private String customerId;
    private String paymentMethod;
    private Date saleDate;
    private Float discount;
    @JsonIgnoreProperties(value = {"sale_bill"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "saleBill")
    private List<SaleProductEntity> saleProducts;
}
