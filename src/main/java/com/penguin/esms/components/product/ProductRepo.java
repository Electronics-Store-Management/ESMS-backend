package com.penguin.esms.components.product;

import com.penguin.esms.components.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, String> {
    Optional<ProductEntity> findByName(String name);
    Optional<ProductEntity> findById(String id);

    List<ProductEntity> findByNameContainingIgnoreCase(String name);
    List<ProductEntity> findByNameContainingIgnoreCaseAndIsStopped(String name, boolean isStopped);

    List<ProductEntity> findByNameContainingIgnoreCaseAndCategoryAndIsStopped(String name, CategoryEntity category, boolean isStopped);

    @Query(value = """
            select ib.supplierId
            from ImportBillEntity ib
            inner join ImportProductEntity ip on ib.id = ip.importBill.id
            where ip.product.id = :productId
            group by ib.supplierId
            """)
    List<String> findSupplierIds(String productId);
}
