package com.testament.veltahleon.entities.history;

import com.testament.veltahleon.entities.places.Nation;
import com.testament.veltahleon.entities.politics.Organization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "languages")
public class Language {

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

    public Language() {}
}
