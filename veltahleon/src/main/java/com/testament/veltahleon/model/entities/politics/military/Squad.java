package com.testament.veltahleon.model.entities.politics.military;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "squads")
public class Squad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "squad_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "squad_leader_id")
    private SquadLeader squadLeader;

    @OneToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "unit_number")
    private Integer unitNumber;

    @Column(name = "description", columnDefinition = "text")
    private StringBuilder description;
}
