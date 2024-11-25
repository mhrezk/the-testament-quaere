package com.testament.veltahleon.model.entities.dogma.mythology;

import com.testament.veltahleon.model.entities.history.Race;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "angels")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Angel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "race_id")
    private Race race;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "image_URL")
    private String imageURL;
}
