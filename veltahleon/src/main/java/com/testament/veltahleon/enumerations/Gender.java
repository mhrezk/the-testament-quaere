package com.testament.veltahleon.enumerations;

import lombok.Getter;

@Getter
public enum Gender {
    FEMALE("Female"),
    MALE("Male");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
