package com.penguin.esms.components.supplier;

import com.penguin.esms.components.supplier.dto.SupplierDTO;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepo supplierRepo;
    private final DTOtoEntityMapper mapper;

    public Optional<SupplierEntity> findById(String supplierId) {
        return supplierRepo.findById(supplierId);
    }

    public List<SupplierEntity> findByName(String name) {
        return supplierRepo.findByNameContainingIgnoreCase(name);
    }

    public SupplierEntity getOne(String id) {
        Optional<SupplierEntity> optionalSupplier = supplierRepo.findById(id);
        if (optionalSupplier.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Supplier not found").toString());
        }
        return optionalSupplier.get();
    }

    public SupplierEntity add(SupplierDTO supplierDTO) {
        SupplierEntity supplier = new SupplierEntity();
        mapper.updateSupplierFromDto(supplierDTO, supplier);
        supplier.setNote(supplierDTO.getNote());
        return supplierRepo.save(supplier);
    }

    public SupplierEntity update(SupplierDTO supplierDTO, String id) {
        Optional<SupplierEntity> optionalSupplier = supplierRepo.findById(id);
        if (optionalSupplier.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Supplier not found").toString());
        }
        SupplierEntity supplier = optionalSupplier.get();
        mapper.updateSupplierFromDto(supplierDTO, supplier);
        if (supplierDTO.getNote() != null) supplier.setNote(supplierDTO.getNote());
        return supplierRepo.save(supplier);
    }

    public SupplierEntity remove(String id) {
        Optional<SupplierEntity> optionalSupplier = supplierRepo.findById(id);
        if (optionalSupplier.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Supplier not found").toString());
        }
        supplierRepo.deleteById(id);
        return optionalSupplier.get();
    }
}
