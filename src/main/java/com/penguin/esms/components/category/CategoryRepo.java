package com.penguin.esms.components.category;

import com.penguin.esms.components.staff.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, String> {
    Optional<CategoryEntity> findByName(String name);
    Optional<CategoryEntity> findById(String id);


}
