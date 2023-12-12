package com.testament.veltahleon.entities.places;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "nation_types")
public class NationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String typeName;

    @Transient
    @Getter
    @Setter
    private Boolean isVassal;

    @Transient
    @Getter
    @Setter
    private Boolean isSuzerain;
}
