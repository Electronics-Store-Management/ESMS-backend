package com.penguin.esms.components.warrantyProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarrantyProductRepo extends JpaRepository<WarrantyProductEntity, String> {
}
