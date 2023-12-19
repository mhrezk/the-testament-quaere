package com.testament.veltahleon.entities.politics;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "militaristic_leaders")
public class MilitaryLeader extends Pundit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private List<Rank> ranks;

    @Getter
    @Setter
    private List<Battle> battles;

    @Getter
    @Setter
    private List<Squad> squads;

    public MilitaryLeader() {}
}
