package com.testament.veltahleon.model.entities.politics;

import com.testament.veltahleon.abstraction.Leader;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

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
