package com.penguin.esms.components.warrantyProduct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class WarrantyProductDTO {
    private String productId;
    private Integer quantity;
    private String warrantyContent;
    private String status;
    private String note;
}
