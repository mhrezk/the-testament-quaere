package com.testament.veltahleon.entities.calendar;

import com.testament.veltahleon.entities.places.Nation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "epochs")
public class Epoch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String epochTime;

    @Getter
    @Setter
    private Integer yearNumber;

    @Getter
    @Setter
    private Nation nation;

    @Getter
    @Setter
    private StringBuilder description;
}
