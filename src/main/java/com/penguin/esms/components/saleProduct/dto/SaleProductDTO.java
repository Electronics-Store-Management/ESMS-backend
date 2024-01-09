package com.penguin.esms.components.saleProduct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class SaleProductDTO {
    private String productId;
    private Integer quantity;
    private Long price;

    public SaleProductDTO(String productId, Integer quantity, Long price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
}
