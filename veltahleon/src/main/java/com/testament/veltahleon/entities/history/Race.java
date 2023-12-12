package com.testament.veltahleon.entities.history;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String racialName;

    @Getter
    @Setter
    private StringBuilder racialDescription;

    public Race() {}
}
