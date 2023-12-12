package com.testament.veltahleon.entities.places;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String locationName;

    @Getter
    @Setter
    private Nation nation;

    @Getter
    @Setter
    private StringBuilder locationDescription;

    @Getter
    @Setter
    private Boolean isCapital;
}
