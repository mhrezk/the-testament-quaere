package com.testament.veltahleon.entities.politics;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "squads")
public class Squad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private MilitaryLeader leaderName;

    @Getter
    @Setter
    private String squadName;

    @Getter
    @Setter
    private Unit unit;

    @Getter
    @Setter
    private Integer unitNumber;

    @Getter
    @Setter
    private List<Battle> battles;
}
