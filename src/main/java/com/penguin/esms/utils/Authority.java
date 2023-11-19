package com.penguin.esms.utils;

import com.penguin.esms.components.permission.EntityType;
import com.penguin.esms.components.permission.PermissionType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Authority {
    EntityType entityType;
    PermissionType permissionType;
    String entityId;

    public Authority(EntityType entityType, PermissionType permissionType, String entityId) {
        this.entityType = entityType;
        this.permissionType = permissionType;
        this.entityId = entityId;
    }

    @Override
    public String toString() {
        return permissionType + ":" + entityType + (entityId != null ? ":" + entityId : "");
    }

    public static final String fromList(Authority... authorities) {
        final String str =  Stream.of(authorities).map(Object::toString).collect(Collectors.joining(" or "));
        return str;
    }
}
