package com.testament.veltahleon.model.entities.politics;

import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.politics.enumeration.RankType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ranks")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rank_id")
    private Long id;

    @Column(name = "rank_name")
    private String rankName;

    @Column(name = "rank_above")
    private String rankAbove;

    @Column(name = "rank_below")
    private String rankBelow;

    @Enumerated(EnumType.STRING)
    @Column(name = "rank_type")
    private RankType rankType;

    @OneToOne
    @JoinColumn(name = "national_id")
    private Nation nation;

    @Column(name = "description", columnDefinition = "text")
    private StringBuilder description;

    @Column(name = "image_URL")
    private String imageURL;
}
