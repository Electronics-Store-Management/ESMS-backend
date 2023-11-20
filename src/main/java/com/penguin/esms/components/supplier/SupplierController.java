package com.penguin.esms.components.supplier;

import com.penguin.esms.components.permission.EntityType;
import com.penguin.esms.components.permission.PermissionEntity;
import com.penguin.esms.components.permission.PermissionRepo;
import com.penguin.esms.components.permission.PermissionType;
import com.penguin.esms.components.permission.dto.ItemPermission;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.components.staff.Role;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.components.supplier.dto.SupplierDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "supplier")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService service;
    private final PermissionRepo permissionRepo;

    @GetMapping
    public List<SupplierEntity> getAl(@RequestParam(defaultValue = "") String name) {
        return service.findByName(name);
    }

    @GetMapping("{id}")
    public SupplierEntity getOne(@PathVariable String id) {
        return service.getOne(id);
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid SupplierDTO supplierDTO) {
        return ResponseEntity.ok(service.add(supplierDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> put(@Valid SupplierDTO supplierDTO, @PathVariable String id) {
        return ResponseEntity.ok(service.update(supplierDTO, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        SupplierEntity supplier = service.remove(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("permission/{id}")
    public ResponseEntity<?> listPermission(Principal connectedUser, @PathVariable String id) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (staff.getRole() == Role.ADMIN) return ResponseEntity.ok(new ItemPermission(true, true, true));
        List<PermissionEntity> permissions = permissionRepo.findByEntityTypeAndEntityIdAndStaffId(EntityType.PRODUCT, id, staff.getId());
        return ResponseEntity.ok(new ItemPermission(permissions));
    }
}
