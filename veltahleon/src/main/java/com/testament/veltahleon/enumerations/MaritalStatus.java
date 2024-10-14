package com.testament.veltahleon.enumerations;

import lombok.Getter;

@Getter
public enum MaritalStatus {
    SINGLE("SINGLE"),
    MARRIED("MARRIED"),
    DIVORCED("DIVORCED"),
    WIDOWED("WIDOWED");

    private final String maritalStatus;

    MaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
