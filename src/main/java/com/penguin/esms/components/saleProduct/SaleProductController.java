package com.penguin.esms.components.saleProduct;

import com.penguin.esms.components.importProduct.ImportProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path ="sale_product")
public class SaleProductController {
    private final SaleProductService service;
    @GetMapping("{id}")
    public SaleProductEntity getSaleProduct(@PathVariable String id) {
        return service.getSaleProduct(id);
    }
}
