package com.testament.veltahleon.entities.calendar;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "years")
public class Year {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private Day day;

    @Getter
    @Setter
    private Month month;

    @Getter
    @Setter
    private Epoch epoch;

    public Year() {}
}
