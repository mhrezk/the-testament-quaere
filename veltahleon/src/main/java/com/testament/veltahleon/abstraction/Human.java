package com.testament.veltahleon.abstraction;

import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.model.entities.history.Race;
import com.testament.veltahleon.model.entities.society.enumeration.Gender;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Human {

    @Column(name = "name")
    private String personalName;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "race_id")
    //@PrimaryKeyJoinColumn(name = "race") //Used instead of @Column with @OneToOne
    private Race personalRace;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender personalGender;

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

    @Column(name = "biography", columnDefinition = "longtext")
    private String personalBiography;

    @Column(name = "image_URL")
    private String imageURL;
}
