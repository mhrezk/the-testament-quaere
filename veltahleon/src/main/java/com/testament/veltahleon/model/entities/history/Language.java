package com.testament.veltahleon.model.entities.history;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @NotNull(message = "Language name cannot be null!")
    @NotBlank(message = "Language name cannot be blank!")
    @NotEmpty(message = "Language name cannot be empty!")
    private String name;

//    @EqualsAndHashCode.Exclude //used to exclude field from equals() and hasCode() methods
    //@JsonIgnore
//    @OneToMany(mappedBy = "language")
//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//        CascadeType.DETACH,
//        CascadeType.MERGE,
//        CascadeType.PERSIST})
//    @JoinTable(name = "letters_languages", joinColumns = @JoinColumn(name = "language_id"),
//        inverseJoinColumns = @JoinColumn(name = "letter_id"))
//    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private List<Letter> letters;

    //@EqualsAndHashCode.Exclude //to prevent StackOverflowError from recursion in hashCode() method because of Sets
    //@JsonIgnore
//    @OneToMany(mappedBy = "nationalLanguage", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    private List<Nation> nations;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;

    @Column(name = "alphabet_URL")
    private String alphabetURL;

    //Convenience Methods (for bidirectional relationship)
//    public void addLetter(Letter letter) {
//        if(letters == null) {
//            letters = new ArrayList<>();
//        }
//        letters.add(letter);
//        //letter.setLanguage(this); for Many-to-One, not Many-to-Many
//    }

//    public void addNation(Nation nation) {
//        if(nations == null) {
//            nations = new ArrayList<>();
//        }
//        nations.add(nation);
//        nation.setNationalLanguage(this);
//    }
}
