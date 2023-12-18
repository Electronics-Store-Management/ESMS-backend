package com.penguin.esms.components.saleProduct;

import com.penguin.esms.components.importProduct.ImportProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleProductRepo extends JpaRepository<SaleProductEntity,String> {
    Optional<SaleProductEntity> findById(String id);
    Optional<SaleProductEntity> findByImportSaleId(String importSaleId);
}
