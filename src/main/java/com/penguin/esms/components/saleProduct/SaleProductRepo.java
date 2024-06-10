package com.penguin.esms.components.saleProduct;

import com.penguin.esms.components.importProduct.ImportProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleProductRepo extends JpaRepository<SaleProductEntity,String> {
    Optional<SaleProductEntity> findById(String id);
    List<SaleProductEntity> findBySaleBillId(String saleBillId);

    @Query(value = """
        SELECT SUM(sp.quantity) FROM SaleProductEntity sp
        WHERE sp.product.id = :productId
        """)
    Integer getQuantity(String productId);
}
