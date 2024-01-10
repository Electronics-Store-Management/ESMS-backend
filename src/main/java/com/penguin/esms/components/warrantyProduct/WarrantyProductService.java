package com.penguin.esms.components.warrantyProduct;

import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.product.ProductRepo;
import com.penguin.esms.components.product.ProductService;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.components.saleBill.SaleBillRepo;
import com.penguin.esms.components.saleProduct.SaleProductEntity;
import com.penguin.esms.components.saleProduct.SaleProductRepo;
import com.penguin.esms.components.saleProduct.dto.SaleProductDTO;
import com.penguin.esms.components.warrantyBill.WarrantyBillRepo;
import com.penguin.esms.components.warrantyProduct.dto.WarrantyProductDTO;
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
public class WarrantyProductService {
    private final ProductService productService;
    private final WarrantyProductRepo repo;
    private final ProductRepo productRepo;
    private final WarrantyBillRepo warrantyRepo;
    private final DTOtoEntityMapper mapper;
    public WarrantyProductEntity getWarrantyProduct(String warrantyProductId) {
        Optional<WarrantyProductEntity> warrantyProduct = repo.findById(warrantyProductId);
        if (warrantyProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return warrantyProduct.get();
    }
    public WarrantyProductEntity getWarrantyBill(String warrantyBillId) {
        Optional<WarrantyProductEntity> warrantyProduct = repo.findById(warrantyBillId);
        if (warrantyProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return warrantyProduct.get();
    }

    public WarrantyProductDTO random() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "123456789";
        ProductDTO productDTO = productService.random();
        ProductEntity productEntity = productService.add(productDTO);
        String productId = productEntity.getId();
        String warrantyContent = Random.random(20, characters);
        String status = Random.random(20, characters);
        String note = Random.random(20, characters);
        Integer quantity = Integer.valueOf(Random.random(Integer.valueOf(Random.random(1, "12")), numbers));
        return new WarrantyProductDTO(productId, quantity, warrantyContent, status, note);
    }
}
