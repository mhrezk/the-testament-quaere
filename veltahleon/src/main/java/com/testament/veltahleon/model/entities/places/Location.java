package com.testament.veltahleon.model.entities.places;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "current_name")
    private String locationCurrentName;

    @Column(name = "previous_name")
    private String locationPreviousName;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Nation nation;

    @Column(name = "description", columnDefinition = "text")
    private String locationDescription;
}
