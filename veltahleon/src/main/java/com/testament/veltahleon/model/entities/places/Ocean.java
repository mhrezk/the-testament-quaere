package com.testament.veltahleon.model.entities.places;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "oceans")
@EqualsAndHashCode
@ToString
public class Ocean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

}
