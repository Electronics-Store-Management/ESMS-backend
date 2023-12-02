package com.penguin.esms.components.warrantyProduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.saleBill.SaleBillEntity;
import com.penguin.esms.components.warrantyBill.WarrantyBillEntity;
import com.penguin.esms.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
public class WarrantyProductEntity extends NoteEntity {
    private String staffId;
    private Integer quantity;
    private String warrantyContent;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties(value = {"warrantyProducts"})
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "warrantyBill_id")
    @JsonIgnoreProperties(value = {"warrantyProducts"})
    private WarrantyBillEntity warrantyBill;

}
