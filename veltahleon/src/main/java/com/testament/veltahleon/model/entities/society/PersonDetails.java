package com.testament.veltahleon.model.entities.society;

import com.testament.veltahleon.enumerations.ClosedAnswer;
import com.testament.veltahleon.enumerations.Lineage;
import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.politics.Rank;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "people_details")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PersonDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REMOVE
    })
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "birth_day")
    private Integer birthDay;

    @Column(name = "birth_month")
    private Integer birthMonth;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "death_day")
    private Integer deathDay;

    @Column(name = "death_month")
    private Integer deathMonth;

    @Column(name = "death_year")
    private Integer deathYear;

    @Column(name = "year_abbreviation")
    private String yearAbbreviation;

//    @Column(name = "calendar_system")
//    private String calendarSystem;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "religion_id")
    private Religion religion;

//    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "family_id", referencedColumnName = "id")
//    private Family family;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "birth_year_id")
//    private Year birthYear;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "death_year_id")
//    private Year deathYear;

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
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "rank_id")
    private Rank rank;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "title_id")
//    //@Column(name = "epithet") //cannot be used with @OneToOne
//    //@PrimaryKeyJoinColumn(name = "epithet")
//    private Title title;

    @Column(name = "epithet")
    private String epithet;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "is_bastard")
//    private ClosedAnswer isBastard;

    @Enumerated(EnumType.STRING)
    @Column(name = "lineage")
    private Lineage lineage;

    @Column(name = "biography", columnDefinition = "longtext")
    private String biography;

    @Column(name = "image_URL")
    private String imageURL;

    public String getBirthDate() {
        return birthDay + "/" + birthMonth + "/" + birthYear;
    }

    public String getDeathDate() {
        return deathDay + "/" + deathMonth + "/" + deathYear;
    }
}
