package com.penguin.esms.components.supplier;

import com.penguin.esms.components.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepo extends JpaRepository<SupplierEntity, String> {
    public Optional<SupplierEntity> findByName(String name);

    public List<SupplierEntity> findByNameContainingIgnoreCaseAndIsStopped(String name, boolean isStopped);

    Optional<SupplierEntity> findById(String id);

    @Query(value = """
            select p
            from ProductEntity p
            join ImportProductEntity ip on ip.product.id = p.id
            join ImportBillEntity ib on ip.importBill.id = ib.id
            where ib.supplierId = :supplierId
            group by p
            """)
    List<ProductEntity> findProductList(String supplierId);
}
