package com.testament.veltahleon.entities.politics;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "armies")
public class Army {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String armyName;

    @Getter
    @Setter
    private MilitaryLeader armyLeader;

    @Getter
    @Setter
    private List<Squad> squads;

    @Getter
    @Setter
    private List<Battle> battles;
}
