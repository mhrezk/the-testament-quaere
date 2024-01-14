package com.testament.veltahleon.model.entities.religion.mythology;

import com.testament.veltahleon.model.entities.history.Race;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "faes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fae {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "race_id")
    private Race race;

    @Column(columnDefinition = "text")
    private StringBuilder description;
}
