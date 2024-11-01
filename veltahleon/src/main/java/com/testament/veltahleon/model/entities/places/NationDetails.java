package com.testament.veltahleon.model.entities.places;

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

    @NotNull(message = "Ruling party cannot be null!")
    @NotBlank(message = "Ruling party cannot be blank!")
    @NotEmpty(message = "Ruling party cannot be empty!")
    @Column(name = "ruling_party_name")
    private String rulingPartyName;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REMOVE
    })
    @JoinColumn(name = "nation")
    private Nation nation;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "capital_id")
    private Capital capital;

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

    @Column(name = "description", columnDefinition = "longtext")
    private String description;

    @Column(name = "flag_url", nullable = true)
    private String flagURL;
}
