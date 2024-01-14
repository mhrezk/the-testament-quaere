package com.testament.veltahleon.model.entities.places;

import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.politics.NationLeader;
import com.testament.veltahleon.model.entities.politics.Pundit;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.Set;

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

    @OneToOne
    @JoinColumn(name = "capital_id")
    private Capital nationalCapital;

    @OneToOne
    @JoinColumn(name = "continent_id")
    private Continent nationalContinent;

    @OneToOne
    @JoinColumn(name = "nation_leader_id")
    private NationLeader nationalLeader;

    @OneToOne
    @JoinColumn(name = "language_id")
    private Language nationalLanguage;

    @OneToMany(mappedBy = "provincialNation")
    private Set<Province> nationalProvinces;

    @OneToOne
    @JoinColumn(name = "nation_type_id")
    private NationType nationalType;

    @OneToMany(mappedBy = "nation")
    private Set<Pundit> pundits;

    @Column(name = "description", columnDefinition = "longtext")
    private StringBuilder nationalDescription;
}
