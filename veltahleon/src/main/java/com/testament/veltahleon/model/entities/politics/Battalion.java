package com.testament.veltahleon.model.entities.politics;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "battalions")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Battalion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String armyName;

    @OneToOne
    @JoinColumn(name = "military_leader_id")
    private MilitaryLeader battalionLeader;

    @OneToMany
    @JoinColumn(name = "battalion_id")
    private List<Squad> squads;
}
