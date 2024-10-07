package com.testament.veltahleon.enumerations;

import lombok.Getter;

@Getter
public enum ClosedAnswer {
    YES("Yes"),
    NO("No");

    private final String answer;

    ClosedAnswer(String answer) {
        this.answer = answer;
    }
}
