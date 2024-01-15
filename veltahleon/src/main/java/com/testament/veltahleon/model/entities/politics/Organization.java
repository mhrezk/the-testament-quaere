package com.testament.veltahleon.model.entities.politics;

import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.society.Person;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String organizationalName;

    @ManyToOne
    @JoinColumn(name = "pundit_id")
    private Pundit founder;

    @OneToOne
    @JoinColumn(name = "national_id")
    private Nation nation;

    @OneToMany
    @MapKeyColumn(name = "foundation_or_disbandment")
    @Column(name = "year")
    private Map<String, Year> yearFoundationAndDisbandment;

    @Column(name = "coat_of_arms_url")
    private String urlCoatOfArms;
}
