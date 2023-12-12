package com.testament.veltahleon.entities.mythology;

import com.testament.veltahleon.entities.places.Nation;
import com.testament.veltahleon.entities.politics.Organization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "deities")
public class Deity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Nation nationalAffiliation;

    @Getter
    @Setter
    private Organization organizationalAffiliation;

    @Getter
    @Setter
    private StringBuilder description;

    public Deity() {}
}
