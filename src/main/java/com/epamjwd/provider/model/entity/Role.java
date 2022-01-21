package com.epamjwd.provider.model.entity;

public enum Role {
    ADMIN("Admin"),
    USER("User");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
