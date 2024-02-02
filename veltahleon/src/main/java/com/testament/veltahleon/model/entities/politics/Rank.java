package com.testament.veltahleon.model.entities.politics;

import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.politics.enumeration.RankType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ranks")
@ToString
@EqualsAndHashCode
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rank_id")
    private Long id;

    @Column(name = "rank_name")
    private String name;

    @Column(name = "rank_above")
    private String rankAbove;

    @Column(name = "rank_below")
    private String rankBelow;

    @Enumerated(EnumType.STRING)
    @Column(name = "rank_type")
    private RankType rankType;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "national_id")
    private Nation nation;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "image_URL")
    private String imageURL;
}
