package com.penguin.esms.components.importProduct;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ImportProductRepo extends JpaRepository<ImportProductEntity, String> {
    Optional<ImportProductEntity> findById(String id);
    List<ImportProductEntity> findByImportBillId(String importBillId);

    @Query(value = """
        SELECT COALESCE(SUM(ip.quantity), 0) FROM ImportProductEntity ip
        WHERE ip.product.id = :productId
        """)
    Integer getQuantity(String productId);
}
