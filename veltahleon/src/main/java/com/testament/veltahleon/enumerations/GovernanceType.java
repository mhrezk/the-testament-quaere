package com.testament.veltahleon.enumerations;

import lombok.Getter;

@Getter
public enum GovernanceType {
    AUTHORITARIAN("AUTHORITARIAN"),
    DEMOCRATIC("DEMOCRATIC"),
    PARLIAMENTARY("PARLIAMENTARY"),
    THEOCRATIC("THEOCRATIC"),
    NONE("NONE");

    private final String governanceType;

    GovernanceType(String governanceType) {
        this.governanceType = governanceType;
    }
}
