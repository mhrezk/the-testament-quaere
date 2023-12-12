package com.testament.veltahleon.entities.places;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "continents")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String continentalName;

    @Getter
    @Setter
    private StringBuilder continentalDescription;
}
