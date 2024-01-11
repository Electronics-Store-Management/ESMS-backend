package com.penguin.esms.controller;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.category.CategoryService;
import com.penguin.esms.components.importBill.ImportBillService;
import com.penguin.esms.components.importBill.dto.ImportBillDTO;
import com.penguin.esms.components.permission.PermissionRepo;
import com.penguin.esms.components.permission.PermissionService;
import com.penguin.esms.components.permission.dto.PermissionRequest;
import com.penguin.esms.components.product.ProductService;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.components.saleBill.SaleBillService;
import com.penguin.esms.components.saleBill.dto.SaleBillDTO;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.components.supplier.SupplierService;
import com.penguin.esms.components.supplier.dto.SupplierDTO;
import com.penguin.esms.components.warrantyBill.WarrantyBillService;
import com.penguin.esms.components.warrantyBill.dto.WarrantyBillDTO;
import com.penguin.esms.services.AmazonS3Service;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping(path = "data")
@RequiredArgsConstructor
public class Data {
    private final ImportBillService importBillService;
    private final ProductService productService;
    private final AmazonS3Service amazonS3Service;
    private final SupplierService supplierService;
    private final PermissionRepo permissionRepo;
    private final PermissionService permissionService;
    private final CategoryService categoryService;
    private final WarrantyBillService warrantyBillService;
    private final SaleBillService saleBillService;

    @PostMapping("import")
    public void randomImport(Principal connectedUser) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        for (int i = 0; i <= 10; i++)
            importBillService.postImportBill(importBillService.random(), staff);
    }

    @PostMapping("sale")
    public void randomSale(Principal connectedUser) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        for (int i = 0; i <= 20; i++)
            saleBillService.post(saleBillService.random(), staff);
    }

    @PostMapping("warranty")
    public void randomWarranty(Principal connectedUser) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        for (int i = 0; i <= 10; i++)
            warrantyBillService.postWarrantyBill(warrantyBillService.random(), staff);
    }

}
