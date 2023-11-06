package com.penguin.esms.components.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, String> {
    Optional<ProductEntity> findByName(String name);
    List<ProductEntity> findByNameContainingIgnoreCase(String name);
}
