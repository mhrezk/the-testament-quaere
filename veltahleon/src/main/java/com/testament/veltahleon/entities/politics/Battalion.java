package com.testament.veltahleon.entities.politics;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@Table(name = "battalions")
public class Battalion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private MilitaryLeader leaderName;

    @Getter
    @Setter
    private List<Unit> units;

    @Getter
    @Setter
    private Pundit armyLeader;

    @Getter
    @Setter
    private List<Battle> battles;
}
