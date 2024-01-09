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


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<?> postImport(@RequestBody ImportBillDTO importBillDTO, Principal connectedUser) {
//        return ResponseEntity.ok(importBillService.postImportBill(importBillDTO, staff));
//    }

//    @PostMapping
//    public ResponseEntity<?> postSupplier(@Valid SupplierDTO supplierDTO) {
//        return ResponseEntity.ok(supplierService.add(supplierDTO));
//    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<?> postCategory(CategoryEntity categoryEntity) {
//        return ResponseEntity.ok(categoryService.postCategory(categoryEntity));
//    }
//
//    @PostMapping
//    public ResponseEntity<?> postProduct(@RequestParam @Nullable MultipartFile photo, @Valid ProductDTO productDTO) throws IOException {
//        if (photo != null) {
//            String objectURL = amazonS3Service.addFile(photo, productDTO.getName() + "_" + photo.getOriginalFilename());
//            productDTO.setPhotoURL(objectURL);
//        }
//        return ResponseEntity.ok(productService.add(productDTO));
//    }

//    @PostMapping
//    @PreAuthorize("hasAuthority('UPDATE_ALL:PERMISSION') or hasAuthority('UPDATE_ITEM:PERMISSION:#staffId') or hasAuthority('ADMIN')")
//    public ResponseEntity<?> addPermission(@RequestBody PermissionRequest permissionRequest) {
//        return ResponseEntity.ok(permissionService.add(permissionRequest, permissionRequest.getStaffId()));
//    }
    @PostMapping
    public void randomData(Principal connectedUser){
        System.out.println("lozzz");
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        importBillService.postImportBill(importBillService.random(), staff);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<?> post() {
////        importBillService.postImportBill(importBillDTO, connectedUser);
//        return null;
//    }
}
