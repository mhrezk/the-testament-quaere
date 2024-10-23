package com.testament.veltahleon.model.entities.history;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "races")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;

    @Column(name = "lifespan_min")
    private Integer minimumLifespan;

    @Column(name = "lifespan_max")
    private Integer maximumLifespan;

    @Column(name = "image_URL")
    private String imageURL;

//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "race_id")
//    private List<SubRace> subRaces;
}
