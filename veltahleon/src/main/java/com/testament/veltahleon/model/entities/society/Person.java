package com.testament.veltahleon.model.entities.society;

import com.testament.veltahleon.enumerations.ClosedAnswer;
import com.testament.veltahleon.enumerations.Gender;
import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.model.entities.history.Race;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "people")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null!")
    @NotBlank(message = "Name cannot be blank!")
    @NotEmpty(message = "Name cannot be empty!")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Name cannot be null!")
    @NotBlank(message = "Name cannot be blank!")
    @NotEmpty(message = "Name cannot be empty!")
    @Column(name = "second_name")
    private String secondName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "race_id", referencedColumnName = "id")
    private Race raceName;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "religion_id")
//    private Religion religion;

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
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "nation_id")
//    private Nation nation;
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "job_id")
//    private Job job;
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "rank_id")
//    private Rank rank;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "title_id")
//    //@Column(name = "epithet") //cannot be used with @OneToOne
//    //@PrimaryKeyJoinColumn(name = "epithet")
//    private Title title;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "is_bastard")
//    private ClosedAnswer isBastard;
//
//    @Column(name = "biography", columnDefinition = "longtext")
//    private String biography;
//
//    @Column(name = "image_URL")
//    private String imageURL;

//    @OneToMany
////    @ElementCollection(fetch = FetchType.LAZY)
////    @CollectionTable(name = "siblings_people", joinColumns = @JoinColumn(name = "people_id"))
////    @OrderColumn(name = "siblings_index")
//    @Column(name = "siblings")
//    private List<String> siblings;

//    @OneToMany
////    @ElementCollection(fetch = FetchType.LAZY)
////    @CollectionTable(name = "children_people", joinColumns = @JoinColumn(name = "people_id"))
////    @OrderColumn(name = "children_index")
//    @Column(name = "children")
//    private List<String> children;

//    @OneToMany
////    @ElementCollection(fetch = FetchType.LAZY)
////    @CollectionTable(name = "spouses_people", joinColumns = @JoinColumn(name = "people_id"))
////    @OrderColumn(name = "spouses_index")
//    @Column(name = "spouses")
//    private List<String> spouses;
}
