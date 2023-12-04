package com.testament.veltahleon.entities.calendar;

import com.testament.veltahleon.entities.history.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "months")
public class Month {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Language language;

    @Getter
    @Setter
    private Integer numberOfDays;

    @Getter
    @Setter
    private StringBuilder description;
}
