package com.testament.veltahleon.enumerations;

public enum RankType {
    NAVAL("NAVAL"),
    AERIAL("AERIAL"),
    TERRESTRIAL("TERRESTRIAL"),
    NONE("NONE");

    private final String rankType;

    RankType(String rankType) {
        this.rankType = rankType;
    }
}
