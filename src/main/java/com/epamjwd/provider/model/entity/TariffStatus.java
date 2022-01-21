package com.epamjwd.provider.model.entity;

public enum TariffStatus {
    ACTIVE("Active"),
    ARCHIVE("Archive"),
    DEACTIVATED("Deactivated");

    private final String status;

    TariffStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
