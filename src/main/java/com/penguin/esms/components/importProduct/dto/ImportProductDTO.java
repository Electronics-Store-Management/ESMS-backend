package com.penguin.esms.components.importProduct.dto;

import com.penguin.esms.components.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ImportProductDTO {
    private String productId;
    private Integer quantity;
    private Long price;

}
