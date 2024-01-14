package com.testament.veltahleon.model.entities.history;

import com.testament.veltahleon.model.entities.places.Nation;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "languages")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "language")
    private Set<Letter> letters;

    @OneToOne(mappedBy = "nationalLanguage")
    private Nation nationalAffiliation;

    @Column(name = "description", columnDefinition = "longtext")
    private StringBuilder description;
}
