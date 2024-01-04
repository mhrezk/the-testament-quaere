package com.testament.veltahleon.entities.society;

import com.testament.veltahleon.entities.society.enumeration.Lineage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "houses")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Lineage lineage;
}
