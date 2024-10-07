package com.testament.veltahleon.enumerations;

import lombok.Getter;

@Getter
public enum JobType {
    MILITARISTIC("Military"),
    GOVERNMENTAL("Public"),
    MUNICIPAL("Municipality"),
    CIVIL("Private"),
    SERVILE("Slave"),
    CRIMINAL("Criminal"),
    NONE("None");

    private final String jobType;

    JobType(String jobType) {
        this.jobType = jobType;
    }
}
