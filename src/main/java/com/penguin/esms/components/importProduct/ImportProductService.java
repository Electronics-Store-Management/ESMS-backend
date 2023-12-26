package com.penguin.esms.components.importProduct;

import com.penguin.esms.components.importBill.ImportBillRepo;
import com.penguin.esms.components.product.ProductRepo;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
