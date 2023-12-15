package com.penguin.esms.components.importBill;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.importBill.dto.ImportBillDTO;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.entity.Error;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ImportBillService {
    private final ImportBillRepo importBillRepo;
    public ImportBillEntity getImportBill(String importBillId) {
        Optional<ImportBillEntity> importBill = importBillRepo.findById(importBillId);
        if (importBill.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Import bill not found");
        }
        return importBill.get();
    }

    public ImportBillEntity postImportBill(ImportBillEntity importBillEntity) {
        return importBillRepo.save(importBillEntity);
    }
}
