package com.penguin.esms.components.importBill;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.permission.dto.PermissionRequest;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.product.dto.ProductDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "import")
@RequiredArgsConstructor
public class ImportBillController {
    private final ImportBillService importBillService;
    @GetMapping("{id}")
    public ImportBillEntity get(@PathVariable String id) {
        return importBillService.getImportBill(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> post(@RequestBody ImportBillEntity importBillEntity) {
        return ResponseEntity.ok(importBillService.postImportBill(importBillEntity));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        ImportBillEntity importBill = importBillService.removeImportBill(id);
        return ResponseEntity.ok().build();
    }
}
