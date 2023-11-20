package com.penguin.esms.components.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepo extends JpaRepository<SupplierEntity, String> {
    public List<SupplierEntity> findByNameContainingIgnoreCase(String name);
}
