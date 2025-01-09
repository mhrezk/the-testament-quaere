package com.testament.veltahleon.enumerations;

import lombok.Getter;

@Getter
public enum Gender {
    FEMALE("FEMALE"),
    MALE("MALE");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
