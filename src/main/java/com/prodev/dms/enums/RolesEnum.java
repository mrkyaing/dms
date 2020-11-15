package com.prodev.dms.enums;

public enum RolesEnum {

    MANAGER(1, "manager"),
    DELIVERY(2, "Deli_man"),
    STAFF(3, "staff");

    private final int id;

    private final String roleName;

    RolesEnum(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
}
