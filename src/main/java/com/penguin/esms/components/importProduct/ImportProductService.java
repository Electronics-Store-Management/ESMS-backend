package com.penguin.esms.components.importProduct;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.category.CategoryRepo;
import com.penguin.esms.components.importBill.ImportBillRepo;
import com.penguin.esms.components.importProduct.dto.ImportProductDTO;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.product.ProductRepo;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.components.supplier.SupplierRepo;
import com.penguin.esms.entity.Error;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import com.penguin.esms.services.AmazonS3Service;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
//@Getter
//@Setter
@RequiredArgsConstructor
public class ImportProductService {
    private final ImportProductRepo importProductRepo;
    private final ProductRepo productRepo;
    private final ImportBillRepo importBillRepo;
    private final DTOtoEntityMapper mapper;

//    public ImportProductEntity getImportProduct(String importProductId) {
//        Optional<ImportProductEntity> importProduct = importProductRepo.findById(importProductId);
//        if (importProduct.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
//        }
//        return importProduct.get();
//    }
}
