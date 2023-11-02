package com.penguin.esms.components.permission.requests;

import com.penguin.esms.components.permission.EntityType;
import com.penguin.esms.components.permission.PermissionType;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class PermissionRequest {
    private PermissionType permissionType;
    private EntityType entityType;
    @Nullable
    private String entityId;
}
