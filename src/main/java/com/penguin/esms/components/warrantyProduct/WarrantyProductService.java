package com.penguin.esms.components.warrantyProduct;

import com.penguin.esms.components.product.ProductRepo;
import com.penguin.esms.components.saleBill.SaleBillRepo;
import com.penguin.esms.components.saleProduct.SaleProductEntity;
import com.penguin.esms.components.saleProduct.SaleProductRepo;
import com.penguin.esms.components.warrantyBill.WarrantyBillRepo;
import com.penguin.esms.mapper.DTOtoEntityMapper;
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
}
