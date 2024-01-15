package com.testament.veltahleon.model.entities.politics;

import com.testament.veltahleon.abstraction.Human;
import com.testament.veltahleon.model.entities.calendar.Epoch;

import java.util.Map;
import java.util.Set;

import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.society.Title;
import com.testament.veltahleon.model.enumeration.ClosedAnswer;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pundits")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pundit extends Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nation_id")
    private Nation nation;

//    @ElementCollection
//    @CollectionTable(name = "organizations_people", joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "organizations")
    @OneToMany(mappedBy = "founder")
    private Set<Organization> organization;

    @Column(name = "coat_of_arms_url")
    private String urlCoatOfArms;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "is_vassal")
//    private ClosedAnswer isVassal;
}
