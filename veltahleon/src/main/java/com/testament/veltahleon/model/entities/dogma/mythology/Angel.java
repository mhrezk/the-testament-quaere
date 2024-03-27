package com.testament.veltahleon.model.entities.dogma.mythology;

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

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "image_URL")
    private String imageURL;
}
