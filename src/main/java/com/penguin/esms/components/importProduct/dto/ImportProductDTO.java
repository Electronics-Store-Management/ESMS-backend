package com.penguin.esms.components.importProduct.dto;

import com.penguin.esms.components.product.ProductService;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.utils.Random;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

//@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ImportProductDTO {
    private String productId;
    private Integer quantity;
    private Long price;

    public ImportProductDTO(String productId, Integer quantity, Long price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }



}
