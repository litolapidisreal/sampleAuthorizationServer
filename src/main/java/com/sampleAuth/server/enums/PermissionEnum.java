package com.sampleAuth.server.enums;

public enum PermissionEnum {
    PRODUCT_WRITE("product:write"),
    PRODUCT_READ("product:read"),
    PRODUCT_UPDATE("product:update"),
    PRODUCT_DELETE("product:delete"),
    TRANSACTION_WRITE("transaction:write"),
    TRANSACTION_READ("transaction:read"),
    TRANSACTION_UPDATE("transaction:update");

    private final String permission;

    PermissionEnum(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
