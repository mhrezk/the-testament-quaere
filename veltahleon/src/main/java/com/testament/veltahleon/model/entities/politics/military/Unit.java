package com.testament.veltahleon.model.entities.politics.military;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Long id;

    @Column(name = "unit_type")
    private String unitType;

    @Column(name = "description", columnDefinition = "text")
    private StringBuilder description;
}
