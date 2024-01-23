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
@EqualsAndHashCode
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "language")
    private Set<Letter> letters;

    @OneToMany(mappedBy = "nationalLanguage")
    private Set<Nation> nationalAffiliation;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;
}
