package com.penguin.esms.components.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.customer.CustomerEntity;
import com.penguin.esms.components.importProduct.ImportProductEntity;
import com.penguin.esms.components.saleProduct.SaleProductEntity;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.components.warrantyBill.WarrantyBillEntity;
import com.penguin.esms.components.warrantyProduct.WarrantyProductEntity;
import com.penguin.esms.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity extends BaseEntity {
    @Column(unique = true)
    @NotNull
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties(value = {"products"})
    private CategoryEntity category;
    @ManyToMany
    @JsonIgnoreProperties(value = {"products"})
    private List<SupplierEntity> suppliers;
    private String unit;
    private Long price;
    private Integer quantity;
    private Integer warrantyPeriod;
    private Boolean isAvailable = true;
    private String photoURL;

    @JsonIgnoreProperties(value = {"importProducts"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ImportProductEntity> importProducts;
    @JsonIgnoreProperties(value = {"saleProducts"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<SaleProductEntity> saleProducts;
    @JsonIgnoreProperties(value = {"warrantyProducts"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<WarrantyProductEntity> warrantyProducts;

}
