package com.testament.veltahleon.model.entities.politics.military;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testament.veltahleon.model.entities.society.Person;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "squads")
public class Squad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "squad_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "squad_leader_id")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private SquadLeader leader;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "squad_leader_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Person leader;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "number_of_units")
    private Integer unitNumber;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "symbol_url")
    private String urlSymbol;
}
