package com.testament.veltahleon.model.entities.places;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testament.veltahleon.model.entities.history.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "nation_details")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class NationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null!")
    @NotBlank(message = "Name cannot be blank!")
    @NotEmpty(message = "Name cannot be empty!")
    @Column(name = "leader_first_name")
    private String leaderFirstName;

    @NotNull(message = "Name cannot be null!")
    @NotBlank(message = "Name cannot be blank!")
    @NotEmpty(message = "Name cannot be empty!")
    @Column(name = "leader_second_name")
    private String leaderSecondName;

    @Column(name = "ruling_party_name")
    private String rulingPartyName;

    @Column(name = "ruling_family")
    private String rulingFamily;

    @Column(name = "founding_year")
    private Integer foundingYear;

    @Column(name = "endingYear")
    private Integer endingYear;

    @Column(name = "preceding_nation")
    private String precedingNation;

    @Column(name = "succeeding_nation")
    private String succeedingNation;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REMOVE
    })
    @JoinColumn(name = "nation_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Nation nation;

//    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "capital_id")
//    private Capital capital;

    @Column(name = "province_number", columnDefinition="Integer default '0'")
    //@ColumnDefault("0")
    private Integer provincialNumber;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "language_id", referencedColumnName = "id")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private Language language;

//    @ElementCollection(targetClass = String.class)
//    @CollectionTable(name = "languages", joinColumns = @JoinColumn(name = "nation_details_id"))
//    @Column(name = "languages")
//    private List<String> languages;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="nation_details_languages",
            joinColumns={@JoinColumn(name="nation_details_id")},
            inverseJoinColumns={@JoinColumn(name="language_id")})
    private List<Language> languages;

//    @OneToMany(mappedBy = "nation", cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    private List<Province> provinces;

    @Column(name = "history", columnDefinition = "longtext")
    private String history;

    @Column(name = "flag_URL", nullable = true)
    private String flagURL;
}
