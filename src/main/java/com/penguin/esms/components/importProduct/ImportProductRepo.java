package com.penguin.esms.components.importProduct;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ImportProductRepo extends JpaRepository<ImportProductEntity, String> {
    Optional<ImportProductEntity> findById(String id);
    List<ImportProductEntity> findByImportBillId(String importBillId);
}
