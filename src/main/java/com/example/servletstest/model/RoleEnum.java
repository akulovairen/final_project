package com.example.servletstest.model;

/**
 * Enum for role.
 */
public enum RoleEnum {
    ADMIN("admin"),
    STUDENT("student"),
    TEACHER("teacher");

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
