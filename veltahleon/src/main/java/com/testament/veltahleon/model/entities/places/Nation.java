package com.testament.veltahleon.model.entities.places;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testament.veltahleon.enumerations.GovernanceType;
import com.testament.veltahleon.enumerations.NationType;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.politics.NationLeader;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "nations")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Nation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null!")
    @NotBlank(message = "Name cannot be blank!")
    @NotEmpty(message = "Name cannot be empty!")
    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "capital_id")
    private Capital capital;

//    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "continent_id")
//    private Continent continent;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "nation_leader_id")
    private NationLeader leader;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Language language;

    //private String language;

    @OneToMany(mappedBy = "nation", cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    private Set<Province> provinces;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "nation_type_id")
//    private NationType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "nation_type")
    private NationType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "governance_type")
    private GovernanceType governanceType;

//    @OneToMany(mappedBy = "nation", cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    private List<Pundit> pundits;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;

    @Column(name = "flag_url", nullable = true)
    private String urlFlag;
}
