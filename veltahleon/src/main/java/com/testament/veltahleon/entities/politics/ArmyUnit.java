package com.testament.veltahleon.entities.politics;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "army_units")
public class ArmyUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String unitName;

    @Getter
    @Setter
    private Integer unitNumber;
}
