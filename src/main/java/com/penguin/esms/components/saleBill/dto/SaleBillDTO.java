package com.penguin.esms.components.saleBill.dto;

import com.penguin.esms.components.saleProduct.SaleProductEntity;
import com.penguin.esms.components.saleProduct.dto.SaleProductDTO;
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
    private String id;
    private String customerId;
    private String paymentMethod;
    private Float discount;
    private List<SaleProductDTO> saleProducts;

    public SaleBillDTO(String staffId, String customerId, String paymentMethod, Float discount) {
        this.staffId = staffId;
        this.customerId = customerId;
        this.paymentMethod = paymentMethod;
        this.discount = discount;
    }

    public SaleBillDTO(String staffId, String customerId, String paymentMethod, Float discount,String id ) {
        this.staffId = staffId;
        this.customerId = customerId;
        this.paymentMethod = paymentMethod;
        this.discount = discount;
        this.setId(id);
    }

    public SaleBillDTO(String customerId, String paymentMethod, Float discount, List<SaleProductDTO> saleProducts) {
        this.customerId = customerId;
        this.paymentMethod = paymentMethod;
        this.discount = discount;
        this.saleProducts = saleProducts;
    }
}
