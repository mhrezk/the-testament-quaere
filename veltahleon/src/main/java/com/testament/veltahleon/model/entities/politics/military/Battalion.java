package com.testament.veltahleon.model.entities.politics.military;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "battalions")
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Battalion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "military_leader_id")
    private MilitaryLeader leader;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "battalion_id")
    private List<Squad> squads;
}
