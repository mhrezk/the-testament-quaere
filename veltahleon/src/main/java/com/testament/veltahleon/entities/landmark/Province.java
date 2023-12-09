package com.testament.veltahleon.entities.landmark;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
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
    private StringBuilder provincialDescription;
}
