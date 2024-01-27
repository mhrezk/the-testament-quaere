package com.testament.veltahleon.model.entities.places;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.politics.NationLeader;
import com.testament.veltahleon.model.entities.politics.Pundit;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "nations")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nationalName;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "capital_id")
    private Capital nationalCapital;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "continent_id")
    private Continent nationalContinent;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "nation_leader_id")
    private NationLeader nationalLeader;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Language nationalLanguage;

    @OneToMany(mappedBy = "provincialNation", cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    private List<Province> nationalProvinces;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "nation_type_id")
    private NationType nationalType;

    @OneToMany(mappedBy = "nation", cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    private List<Pundit> pundits;

    @Column(name = "description", columnDefinition = "longtext")
    private String nationalDescription;
}
