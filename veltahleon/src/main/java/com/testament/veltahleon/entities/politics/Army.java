package com.testament.veltahleon.entities.politics;

import com.testament.veltahleon.entities.landmark.Nation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@Table(name = "armies")
public class Army {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private List<ArmyUnit> units;

    @Getter
    @Setter
    private Nation nation;
}
