package com.testament.veltahleon.model.entities.dogma.mythology;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deities")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Deity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "longtext")
    private String description;

    @Column(name = "image_URL")
    private String imageURL;
}
