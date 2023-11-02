package com.penguin.esms.components.permission;

import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.entity.BaseEntity;
import com.penguin.esms.entity.NoteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table
@RequiredArgsConstructor
public class PermissionEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private PermissionType permissionType;
    @Enumerated(EnumType.STRING)
    private EntityType entityType;
    private String entityId;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<StaffEntity> staffs;

    @Override
    public String toString() {
        return permissionType + ":" + entityType + (entityId != null ? ":" + entityId : "");
    }
}
