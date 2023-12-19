package com.testament.veltahleon.entities.politics;

import com.testament.veltahleon.entities.places.Nation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ranks")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String rankName;

    @Getter
    @Setter
    private Hierarchy rankPosition;

    @Getter
    @Setter
    private Nation nation;

    @Getter
    @Setter
    private StringBuilder description;
}
