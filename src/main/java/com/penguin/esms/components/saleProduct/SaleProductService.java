package com.penguin.esms.components.saleProduct;

import com.penguin.esms.components.importBill.ImportBillRepo;
import com.penguin.esms.components.importProduct.ImportProductEntity;
import com.penguin.esms.components.importProduct.ImportProductRepo;
import com.penguin.esms.components.product.ProductRepo;
import com.penguin.esms.components.saleBill.SaleBillRepo;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
//@Getter
//@Setter
@RequiredArgsConstructor
public class SaleProductService {
    private final SaleProductRepo repo;
    private final ProductRepo productRepo;
    private final SaleBillRepo saleRepo;
    private final DTOtoEntityMapper mapper;
//    public SaleProductEntity getSaleProduct(String saleProductId) {
//        Optional<SaleProductEntity> saleProduct = repo.findById(saleProductId);
//        if (saleProduct.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
//        }
//        return saleProduct.get();
//    }
//    public SaleProductEntity getSaleBill(String saleBillId) {
//        Optional<SaleProductEntity> saleProduct = repo.findById(saleBillId);
//        if (saleProduct.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
//        }
//        return saleProduct.get();
//    }
}
