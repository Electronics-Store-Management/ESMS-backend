package com.penguin.esms.components.importBill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportBillRepo extends JpaRepository<ImportBillEntity, String> {
}
