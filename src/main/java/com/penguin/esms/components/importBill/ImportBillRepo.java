package com.penguin.esms.components.importBill;

import com.penguin.esms.components.permission.EntityType;
import com.penguin.esms.components.permission.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ImportBillRepo extends JpaRepository<ImportBillEntity, String> {
    Optional<ImportBillEntity> findById(String id);
//    List<ImportBillEntity> findByCreatedDate(Date createdDate);

}
