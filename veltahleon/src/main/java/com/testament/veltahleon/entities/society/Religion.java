package com.testament.veltahleon.entities.society;

import com.testament.veltahleon.entities.places.Nation;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "religions")
public class Religion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private List<Nation> nations;

    public Religion() {}
}
