package com.testament.veltahleon.model.entities.history;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testament.veltahleon.model.entities.places.Nation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "languages")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull(message = "Language name cannot be null!")
//    @NotBlank(message = "Language name cannot be blank!")
//    @NotEmpty(message = "Language name cannot be empty!")
    @Column(name = "name")
    private String name;

//    @EqualsAndHashCode.Exclude
    //@JsonIgnore
//    @OneToMany(mappedBy = "language")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
        CascadeType.DETACH,
        CascadeType.MERGE,
        CascadeType.PERSIST})
    @JoinTable(name = "letters_languages", joinColumns = @JoinColumn(name = "letter_id"),
        inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<Letter> letters;

    //@EqualsAndHashCode.Exclude //to prevent StackOverflowError from recursion in hashCode() method because of Sets
    //@JsonIgnore
    @OneToMany(mappedBy = "nationalLanguage")
    private List<Nation> nationalAffiliation;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;

    //Convenience Methods (for bidirectional relationship)
    public void addLetter(Letter letter) {
        if(letters == null) {
            letters = new ArrayList<>();
        }
        letters.add(letter);
        //letter.setLanguage(this); for Many-to-One, not Many-to-Many
    }

    public void addNation(Nation nation) {
        if(nationalAffiliation == null) {
            nationalAffiliation = new ArrayList<>();
        }
        nationalAffiliation.add(nation);
        //nation.setNationalLanguage(this);
    }

//    @Override
//    public String toString() {
//        return "Language{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                '}';
//    }
}
