package com.testament.veltahleon.model.entities.politics.military;

import com.testament.veltahleon.abstraction.Leader;
import com.testament.veltahleon.model.entities.politics.Rank;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "militaristic_leaders")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MilitaryLeader extends Leader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "rank_id")
    private Rank rank;

    @OneToOne(mappedBy = "battalionLeader")
    private Battalion battalion;
}