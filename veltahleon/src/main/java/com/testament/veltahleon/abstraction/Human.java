package com.testament.veltahleon.abstraction;

import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.model.entities.history.Race;
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
    private String biography;

    @Column(name = "image_URL")
    private String imageURL;
}
