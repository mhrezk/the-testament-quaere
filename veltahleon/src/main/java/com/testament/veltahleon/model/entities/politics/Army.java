package com.testament.veltahleon.model.entities.politics;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "armies")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Army {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "army_leader_id")
    private ArmyLeader armyLeader;

    @OneToMany
    @JoinColumn(name = "army_id")
    private List<Battalion> battalions;
}
