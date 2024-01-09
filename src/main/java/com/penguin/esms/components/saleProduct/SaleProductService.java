package com.penguin.esms.components.saleProduct;

import com.penguin.esms.components.importBill.ImportBillRepo;
import com.penguin.esms.components.importProduct.ImportProductEntity;
import com.penguin.esms.components.importProduct.ImportProductRepo;
import com.penguin.esms.components.importProduct.dto.ImportProductDTO;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.product.ProductRepo;
import com.penguin.esms.components.product.ProductService;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.components.saleBill.SaleBillRepo;
import com.penguin.esms.components.saleProduct.dto.SaleProductDTO;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import com.penguin.esms.utils.Random;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class SaleProductService {
    private final ProductService productService;
    private final SaleProductRepo repo;
    private final ProductRepo productRepo;
    private final SaleBillRepo saleRepo;
    private final DTOtoEntityMapper mapper;
    public SaleProductEntity getSaleProduct(String saleProductId) {
        Optional<SaleProductEntity> saleProduct = repo.findById(saleProductId);
        if (saleProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return saleProduct.get();
    }
    public SaleProductEntity getSaleBill(String saleBillId) {
        Optional<SaleProductEntity> saleProduct = repo.findById(saleBillId);
        if (saleProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return saleProduct.get();
    }

    public SaleProductDTO random() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "123456789";
        ProductDTO productDTO = productService.random();
        ProductEntity productEntity = productService.add(productDTO);
        String productId = productEntity.getId();
        Long price = Long.valueOf(Random.random(10, numbers));
        Integer quantity = Integer.valueOf(Random.random(3, numbers));
        return new SaleProductDTO(productId, quantity, price);
    }
}
