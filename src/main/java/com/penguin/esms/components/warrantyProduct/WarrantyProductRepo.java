package com.penguin.esms.components.warrantyProduct;

import com.penguin.esms.components.saleProduct.SaleProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarrantyProductRepo extends JpaRepository<WarrantyProductEntity, String> {
    Optional<WarrantyProductEntity> findById(String id);
    List<WarrantyProductEntity> findByWarrantyBillId(String warrantyBillId);
}
