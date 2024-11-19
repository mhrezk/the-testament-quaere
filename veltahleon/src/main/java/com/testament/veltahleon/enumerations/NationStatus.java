package com.testament.veltahleon.enumerations;

import lombok.Getter;

@Getter
public enum NationStatus {
    SUZERAIN("SUZERAIN"),
    VASSAL("VASSAL"),
    INDEPENDENT("INDEPENDENT");

    private final String nationStatus;

    NationStatus(String nationStatus) {
        this.nationStatus = nationStatus;
    }
}
