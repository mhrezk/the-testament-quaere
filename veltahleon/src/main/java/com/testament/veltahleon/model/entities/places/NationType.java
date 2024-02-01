package com.testament.veltahleon.model.entities.places;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nation_types")
@EqualsAndHashCode
@ToString
public class NationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String name;

    @Column(columnDefinition = "text")
    private String description;
}
