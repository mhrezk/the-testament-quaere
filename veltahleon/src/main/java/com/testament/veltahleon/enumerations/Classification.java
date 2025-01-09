package com.testament.veltahleon.enumerations;

import lombok.Getter;

@Getter
public enum Classification {
    PORIFERA("PORIFERA"),
    CNIDARIA("CNIDARIA"),
    PLATYHELMINTHES("PLATYHELMINTHES"),
    NEMATODA("NEMATODA"),
    ANNELIDA("ANNELIDA"),
    MOLLUSCA("MOLLUSCA"),
    ARTHROPODA("ARTHROPODA"),
    ECHINODERMATA("ECHINODERMATA"),
    VERTEBRATA("VERTEBRATA"),
    CEPHALOCHORDATA("CEPHALOCHORDATA"),
    UROCHORDATA("UROCHORDATA"),
    NONE("NONE");

    private final String classification;

    Classification(String classification) {
        this.classification = classification;
    }
}
