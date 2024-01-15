package com.testament.veltahleon.model.entities.religion.mythology;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

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
    private StringBuilder description;

    @Column(name = "image_URL")
    private String imageURL;
}
