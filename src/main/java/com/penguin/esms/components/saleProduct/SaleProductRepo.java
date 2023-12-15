package com.penguin.esms.components.saleProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleProductRepo extends JpaRepository<SaleProductEntity,String> {
}
