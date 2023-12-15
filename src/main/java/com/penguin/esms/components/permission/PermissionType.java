package com.penguin.esms.components.permission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionType {
    CREATE("CREATE"),
    VIEW_ITEM("VIEW_ITEM"), VIEW_ALL("VIEW_ALL"), VIEW_LIST("VIEW_LIST"),
    UPDATE_ITEM("UPDATE_ITEM"), UPDATE_ALL("UPDATE_ALL"),
    DELETE_ITEM("DELETE_ITEM"), DELETE_ALL("DELETE_ALL");

    private final String permissionType;
}
