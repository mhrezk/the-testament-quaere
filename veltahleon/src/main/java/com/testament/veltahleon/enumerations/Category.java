package com.testament.veltahleon.enumerations;

import lombok.Getter;

@Getter
public enum Category {
    INVERTEBRATE("INVERTEBRATE"),
    VERTEBRATE("VERTEBRATE"),
    NONE("NONE");

    private final String category;

    Category(String category) {
        this.category = category;
    }
}
