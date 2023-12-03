package com.penguin.esms.components.permission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntityType {
    PRODUCT("PRODUCT"), CATEGORY("CATEGORY"), CUSTOMER("CUSTOMER"), STAFF("STAFF");

    private final String entityType;
}
