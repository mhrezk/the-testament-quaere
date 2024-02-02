package com.testament.veltahleon.model.entities.politics.military;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "armies")
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Army {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "army_leader_id")
    private ArmyLeader leader;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "army_id")
    private List<Battalion> battalions;
}
