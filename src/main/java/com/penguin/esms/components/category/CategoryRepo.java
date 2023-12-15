package com.penguin.esms.components.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.penguin.esms.components.category.response.FoundCategoryItem;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.components.supplier.SupplierEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepo extends JpaRepository<CategoryEntity, String> {
    Optional<CategoryEntity> findByName(String name);
    List<CategoryEntity> findByNameContainingIgnoreCaseAndIsStopped(String name, boolean isStopped);
    Optional<CategoryEntity> findById(String id);
}
