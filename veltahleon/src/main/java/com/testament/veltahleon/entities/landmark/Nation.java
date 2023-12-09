package com.testament.veltahleon.entities.landmark;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "nations")
public class Nation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String nationalName;

    @Getter
    @Setter
    private Location nationalCapital;

    @Getter
    @Setter
    private Continent nationalContinent;

    @Getter
    @Setter
    private List<Province> nationalprovinces;

    public Nation() {}
}
