package com.testament.veltahleon.model.entities.divination;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "tarots")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tarot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "description", columnDefinition = "text")
    private StringBuilder description;
}
