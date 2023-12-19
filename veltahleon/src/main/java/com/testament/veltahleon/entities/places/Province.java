package com.testament.veltahleon.entities.places;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "provinces")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String provincialName;

    @Getter
    @Setter
    private Nation provincialNation;

    @Getter
    @Setter
    private StringBuilder provincialDescription;
}
