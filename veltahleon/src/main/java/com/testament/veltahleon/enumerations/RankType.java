package com.testament.veltahleon.enumerations;

public enum RankType {
    NAVAL("NAVAL"),
    AERIAL("AERIAL"),
    TERRESTRIAL("TERRESTRIAL");

    private final String rankType;

    RankType(String rankType) {
        this.rankType = rankType;
    }
}
