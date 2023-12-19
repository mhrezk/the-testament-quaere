package com.testament.veltahleon.entities.politics;

import com.testament.veltahleon.entities.places.Nation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "national_leaders")
public class NationLeader extends Pundit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private List<Nation> nations;

    @Getter
    @Setter
    private Boolean isVassal;

    @Getter
    @Setter
    private Boolean isSuzerain;

    public NationLeader() {}
}
