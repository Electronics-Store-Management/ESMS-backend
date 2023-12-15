package com.penguin.esms.components.warrantyBill;

import com.penguin.esms.components.importBill.ImportBillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface WarrantyBillRepo  extends JpaRepository<WarrantyBillEntity, String> {
    Optional<WarrantyBillEntity> findById(String id);
//    List<WarrantyBillEntity> findByCreatedDate(Date createdDate);
}
