package com.testament.veltahleon.model.entities.politics;

import com.testament.veltahleon.abstraction.Human;

import java.util.List;

import com.testament.veltahleon.model.entities.places.Nation;
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
    private List<Organization> organization;

    @Column(name = "coat_of_arms_url")
    private String urlCoatOfArms;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "is_vassal")
//    private ClosedAnswer isVassal;
}
