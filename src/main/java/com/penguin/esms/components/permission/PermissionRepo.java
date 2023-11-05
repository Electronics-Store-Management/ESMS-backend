package com.penguin.esms.components.permission;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepo extends JpaRepository<PermissionEntity,String> {
//    @Query(value = """
//        select t from Token t join StaffEntity s\s
//        on t.user.id = s.id\s
//        where s.id = :id and (t.expired = false or t.revoked = false)\s
//        """)
//    Optional<PermissionEntity> findPermission(String permissionType, String entityType, String entityId, String staffId);

    Optional<PermissionEntity> findByPermissionTypeAndEntityTypeAndEntityIdAndStaffId(String permissionType, String entityType, String entityId, String staffId);
    Optional<PermissionEntity> findById(String id);
}