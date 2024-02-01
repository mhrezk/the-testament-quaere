package com.testament.veltahleon.model.entities.history;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "letters")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Letter name cannot be null!")
    @NotBlank(message = "Letter name cannot be blank!")
    @NotEmpty(message = "Letter name cannot be empty!")
    private String name;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "language_id", referencedColumnName = "id")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
        CascadeType.DETACH,
        CascadeType.MERGE,
        CascadeType.PERSIST})
    @JoinTable(name = "letters_languages", joinColumns = @JoinColumn(name = "letter_id"),
        inverseJoinColumns = @JoinColumn(name = "language_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@JsonIgnore
    private List<Language> languages;

    @Column(name = "script_URL")
    private String scriptURL;

    //Convenience Methods
//    public void addLanguage(Language language) {
//        if(languages == null) {
//            languages = new ArrayList<>();
//        }
//        languages.add(language);
//        language.setLetters(List.of(this));
//    }
}
