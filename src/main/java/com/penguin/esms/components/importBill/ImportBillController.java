package com.penguin.esms.components.importBill;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.importBill.dto.ImportBillDTO;
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
import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "import")
@RequiredArgsConstructor
public class ImportBillController {
    private final ImportBillService importBillService;

    @GetMapping("{id}")
    public List<?> get(@PathVariable String id) {
        return importBillService.getRevisions(id);
    }
    @GetMapping()
    public List<?> get() {
        return importBillService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> post(@RequestBody ImportBillDTO importBillDTO, Principal connectedUser) {
        return ResponseEntity.ok(importBillService.postImportBill(importBillDTO, connectedUser));
    }

    @GetMapping("history")
    public ResponseEntity<?> getAll(@RequestParam long start, @RequestParam long end) {
        return ResponseEntity.ok(importBillService.getAllRevisions(new Date(start), new Date(end)));
    }
}
