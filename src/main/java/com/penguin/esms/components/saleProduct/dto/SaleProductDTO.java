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
    private String seri;
    private Integer quantity;
    private Long price;

    public SaleProductDTO(String productId, Integer quantity, Long price, String seri) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.seri = seri;
    }
}
