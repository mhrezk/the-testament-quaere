package com.testament.veltahleon.model.entities.politics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.society.Person;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organizations")
@EqualsAndHashCode
@ToString
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "pundit_id")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private Pundit founder;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "person_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Person founder;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "national_id")
    private Nation nation;

//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
////    @MapKeyColumn(name = "foundation_or_disbandment")
//    @Column(name = "year")
//    private List<Year> yearFoundationAndDisbandment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "foundation_year_id")
    private Year foundationYear;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "disbandment_year_id")
    private Year disbandmentYear;

    @Column(name = "symbol_url")
    private String urlSymbol;
}
