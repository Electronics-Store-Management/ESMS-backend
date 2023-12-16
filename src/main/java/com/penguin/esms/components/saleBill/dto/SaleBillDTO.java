package com.penguin.esms.components.saleBill.dto;

import com.penguin.esms.components.saleProduct.SaleProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleBillDTO {
    private String staffId;
    private String customerId;
    private String paymentMethod;
    private Date saleDate;
    private Float discount;
    private List<String> saleProducts;

    public SaleBillDTO(String staffId, String customerId, String paymentMethod, Float discount) {
        this.staffId = staffId;
        this.customerId = customerId;
        this.paymentMethod = paymentMethod;
        this.discount = discount;
    }
}
