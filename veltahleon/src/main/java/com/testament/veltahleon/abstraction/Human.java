package com.testament.veltahleon.abstraction;

import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.model.entities.history.Race;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.model.entities.society.Title;
import com.testament.veltahleon.enumerations.Gender;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class Human {

    @NotNull(message = "Name cannot be null!")
    @NotBlank(message = "Name cannot be blank!")
    @NotEmpty(message = "Name cannot be empty!")
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "race_id", referencedColumnName = "id")
    private Race race;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "religion_id")
    private Religion religion;

    //@OneToMany
//    @ElementCollection(fetch = FetchType.LAZY)
//    @CollectionTable(name = "years_birth_death_people", joinColumns = @JoinColumn(name = "people_id"))
    //@MapKeyColumn(name = "birth_or_death")
//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @Column(name = "year")
//    private List<Year> yearBirthAndDeath;

//    @ManyToMany
//    @JoinTable(name = "year_birth_death",
//            joinColumns = @JoinColumn(name = "people_id"),
//            inverseJoinColumns = @JoinColumn(name = "year_id"))
//    private List<Year> yearBirthAndDeath;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "birth_year_id")
    private Year birthYear;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "death_year_id")
    private Year deathYear;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "nation_id")
    private Nation nation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "title_id")
    //@Column(name = "epithet") //cannot be used with @OneToOne
    @PrimaryKeyJoinColumn(name = "epithet")
    private Title title;

    @Column(name = "biography", columnDefinition = "longtext")
    private String biography;

    @Column(name = "image_URL")
    private String imageURL;
}
