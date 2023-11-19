package com.penguin.esms.components.supplier;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepo supplierRepo;

    public Optional<SupplierEntity> findById(String supplierId) {
        return supplierRepo.findById(supplierId);
    }
}
