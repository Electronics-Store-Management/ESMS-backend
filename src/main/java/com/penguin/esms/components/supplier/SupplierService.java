package com.penguin.esms.components.supplier;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.supplier.dto.SupplierDTO;
import com.penguin.esms.entity.Error;
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

    public List<SupplierEntity> findByName(String name) {
        return supplierRepo.findByNameContainingIgnoreCaseAndIsStopped(name, false);
    }

    public List<SupplierEntity> findTermination(String name) {
        return supplierRepo.findByNameContainingIgnoreCaseAndIsStopped(name, true);
    }

    public SupplierEntity getOne(String id) {
        Optional<SupplierEntity> optionalSupplier = supplierRepo.findById(id);
        if (optionalSupplier.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Supplier not found").toString());
        }
        if (optionalSupplier.get().getIsStopped() == true)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Supplier has terminated cooperation");
        return optionalSupplier.get();
    }

    public SupplierEntity add(SupplierDTO supplierDTO) {
        Optional<SupplierEntity> supplierEntityOptional = supplierRepo.findByName(supplierDTO.getName());
        if (supplierEntityOptional.isPresent()) {
            if (supplierEntityOptional.get().getIsStopped() == true)
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, new Error("Supplier has terminated cooperation ").toString());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, new Error("Supplier existed").toString());
        }
        SupplierEntity supplier = new SupplierEntity();
        mapper.updateSupplierFromDto(supplierDTO, supplier);
        supplier.setNote(supplierDTO.getNote());
        supplier.setIsStopped(false);
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

    public void remove(String id) {
        Optional<SupplierEntity> supplierEntityOptional = supplierRepo.findById(id);
        if (supplierEntityOptional.isEmpty()) {
            if (supplierEntityOptional.get().getIsStopped() == true)
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, new Error("Supplier has terminated cooperation ").toString());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Supplier not found").toString());
        }
        supplierEntityOptional.get().setIsStopped(true);
        supplierRepo.save(supplierEntityOptional.get());
    }
}
