package com.testament.veltahleon.model.enumeration;

import lombok.Getter;

@Getter
public enum JobType {
    MILITARISTIC("Military"),
    GOVERNMENTAL("Public"),
    MUNICIPAL("Municipality"),
    CIVIL("Private"),
    SERVILE("Slave"),
    CRIMINAL("Criminal");

    private final String jobType;

    JobType(String jobType) {
        this.jobType = jobType;
    }
}
