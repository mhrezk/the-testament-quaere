package com.testament.veltahleon.enumerations;

import lombok.Getter;

@Getter
public enum Lineage {
    HOUSE("HOUSE"),
    CLAN("CLAN"),
    FAMILY("FAMILY"),
    ORPHAN("ORPHAN"),
    ADOPTED("ADOPTED"),
    NONE("NONE");

    private final String lineage;

    Lineage(String lineage) {
        this.lineage = lineage;
    }
}
