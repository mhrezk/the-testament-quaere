package com.testament.veltahleon.model.entities.divination;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "constellations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Constellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "description", columnDefinition = "text")
    private StringBuilder description;

    @Column(name = "image_URL")
    private String imageURL;
}
