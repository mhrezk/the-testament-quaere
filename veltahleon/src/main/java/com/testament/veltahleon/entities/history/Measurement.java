package com.testament.veltahleon.entities.history;

import com.testament.veltahleon.entities.landmark.Nation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String measurementUnitName;

    @Getter
    @Setter
    private List<Nation> nations;
}
