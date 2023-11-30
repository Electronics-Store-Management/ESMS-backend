package com.penguin.esms.components.importProduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.entity.BaseEntity;
import com.penguin.esms.entity.NoteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
public class ImportProductEntity extends BaseEntity {
    private Integer quantity;
    private Integer index;
    private Long price;
    private String unit;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties(value = {"import_products"})
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "importBill_id")
    @JsonIgnoreProperties(value = {"import_products"})
    private ImportBillEntity importBill;

}
