package com.penguin.esms.components.saleProduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.saleBill.SaleBillEntity;
import com.penguin.esms.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@Audited
public class SaleProductEntity extends BaseEntity {
    private Integer quantity;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties(value = {"saleProducts"})
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "saleBill_id")
    @JsonIgnoreProperties(value = {"saleProducts"})
    private SaleBillEntity saleBill;


}
