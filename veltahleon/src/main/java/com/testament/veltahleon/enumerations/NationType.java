package com.testament.veltahleon.enumerations;

import lombok.*;

@Getter
public enum NationType {
    KINGDOM("KINGDOM"),
    EMPIRE("EMPIRE"),
    FEDERATION("FEDERATION"),
    DUCHY("DUCHY"),
    CITY_STATE("CITY_STATE"),
    REPUBLIC("REPUBLIC");

    private final String nationType;

    NationType(String nationType) {
        this.nationType = nationType;
    }
}
