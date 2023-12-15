package com.penguin.esms.components.importBill;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.importBill.dto.ImportBillDTO;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.entity.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.Principal;
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Import bill not found").toString());
        }
        return importBill.get();
    }

    public ImportBillEntity postImportBill(ImportBillEntity importBillEntity, Principal connectedUser) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        importBillEntity.setStaffId(staff.getId());
        return importBillRepo.save(importBillEntity);
    }
}
