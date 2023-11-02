package com.penguin.esms.components.staff;

import com.penguin.esms.components.staff.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StaffRepository extends JpaRepository<StaffEntity, UUID> {
    Optional<StaffEntity> findByEmail(String email);
    Optional<StaffEntity> findById(String id);
}
