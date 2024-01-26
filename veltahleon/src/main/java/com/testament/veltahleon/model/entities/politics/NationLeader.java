package com.testament.veltahleon.model.entities.politics;

import com.testament.veltahleon.abstraction.Leader;
import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.model.entities.places.Nation;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "national_leaders")
public class NationLeader extends Leader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "nationalLeader")
    private Nation nation;

    @OneToMany
    @Column(name = "year")
    private List<Year> yearBeginningAndEnd;

    @OneToMany(mappedBy = "suzerain")
    private List<Vassal> vassals;
}
