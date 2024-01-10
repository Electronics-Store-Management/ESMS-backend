package com.penguin.esms.components.permission;

import jakarta.validation.constraints.FutureOrPresent;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepo extends JpaRepository<PermissionEntity,String> {
    Optional<PermissionEntity> findByPermissionTypeAndEntityTypeAndEntityIdAndStaffId(String permissionType, String entityType, String entityId, String staffId);
    Optional<PermissionEntity> findById(String id);

    List<PermissionEntity> findByStaffIdAndIsStopped(String id, boolean isStopped);
    List<PermissionEntity> findByEntityTypeAndEntityIdAndStaffId(EntityType entityType, String entityId, String staffId);
}
