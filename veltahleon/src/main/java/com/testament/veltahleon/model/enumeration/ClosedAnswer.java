package com.testament.veltahleon.model.enumeration;

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
