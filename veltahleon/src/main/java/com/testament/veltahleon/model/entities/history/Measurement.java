package com.testament.veltahleon.model.entities.history;

import com.testament.veltahleon.model.entities.places.Nation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "measurements")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_name")
    private String measurementUnitName;

    @Column(name = "unit_abbreviation")
    private String abbreviation;


    @OneToMany
    @JoinColumn(name = "measurement_id")
    private List<Nation> nations;

    @Column(name = "unit_description", columnDefinition = "text")
    private String measurementUnitDescription;
}
