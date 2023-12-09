package com.testament.veltahleon.entities.politics;

import com.testament.veltahleon.entities.landmark.Nation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
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
    private Nation nation;

    @Getter
    @Setter
    private StringBuilder description;
}
