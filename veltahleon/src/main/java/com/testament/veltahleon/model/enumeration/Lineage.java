package com.testament.veltahleon.model.enumeration;

import lombok.Getter;

@Getter
public enum Lineage {
    HOUSE("House"),
    CLAN("Clan"),
    FAMILY("Family"),
    NONE("None");

    private final String lineage;

    Lineage(String lineage) {
        this.lineage = lineage;
    }
}
