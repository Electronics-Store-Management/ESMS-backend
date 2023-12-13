package com.penguin.esms.components.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepo extends JpaRepository<SupplierEntity, String> {
    public Optional<SupplierEntity> findByName(String name);

    public List<SupplierEntity> findByNameContainingIgnoreCaseAndIsStopped(String name, boolean isStopped);
}
