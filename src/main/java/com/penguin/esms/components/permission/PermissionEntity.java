package com.penguin.esms.components.permission;

import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.entity.BaseEntity;
import com.penguin.esms.entity.NoteEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
    private PermissionType permissionType;
    private EntityType entityType;
    private String entityId;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<StaffEntity> staffs;
}
