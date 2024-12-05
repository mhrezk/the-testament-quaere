package com.testament.veltahleon.model.entities.history;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "vocabularies")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Vocabulary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Word cannot be null!")
    @NotBlank(message = "Word cannot be blank!")
    @NotEmpty(message = "Word cannot be empty!")
    private String word;

    @Column(name = "pronunciation")
    private String pronunciation;

    @NotNull(message = "Definition cannot be null!")
    @NotBlank(message = "Definition cannot be blank!")
    @NotEmpty(message = "Definition cannot be empty!")
    private String definition;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;
}
