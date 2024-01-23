package com.testament.veltahleon.model.entities.religion.mythology;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "demons")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Demon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "image_URL")
    private String imageURL;
}
