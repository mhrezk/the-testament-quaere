package com.testament.veltahleon.model.entities.politics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.model.entities.places.Nation;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "pundit_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Pundit founder;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "national_id")
    private Nation nation;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
//    @MapKeyColumn(name = "foundation_or_disbandment")
    @Column(name = "year")
    private List<Year> yearFoundationAndDisbandment;

    @Column(name = "coat_of_arms_url")
    private String urlCoatOfArms;
}
