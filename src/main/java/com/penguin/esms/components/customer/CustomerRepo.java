package com.penguin.esms.components.customer;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.customer.dto.CustomerDTO;
import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity,String> {
    Optional<CustomerEntity> findByName(String name);
    List<CustomerEntity> findByNameContainingIgnoreCaseAndIsStopped(String name, boolean isStopped);
    Optional<CustomerEntity> findById(String id);
    Optional<CustomerEntity> findByPhone(String phone);

}
