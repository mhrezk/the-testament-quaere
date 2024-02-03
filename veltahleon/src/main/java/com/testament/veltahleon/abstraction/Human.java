package com.testament.veltahleon.abstraction;

import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.model.entities.history.Race;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.religion.Religion;
import com.testament.veltahleon.model.entities.society.Family;
import com.testament.veltahleon.model.entities.society.Title;
import com.testament.veltahleon.model.entities.society.enumeration.Gender;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class Human {

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "race_id")
    //@PrimaryKeyJoinColumn(name = "race") //Used instead of @Column with @OneToOne
    private Race race;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "religion_id")
    private Religion religion;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "family_id", referencedColumnName = "id")
    private Family family;

    //@OneToMany
//    @ElementCollection(fetch = FetchType.LAZY)
//    @CollectionTable(name = "years_birth_death_people", joinColumns = @JoinColumn(name = "people_id"))
    //@MapKeyColumn(name = "birth_or_death")
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @Column(name = "year")
    private List<Year> yearBirthAndDeath;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "nation_id")
    private Nation nation;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
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
