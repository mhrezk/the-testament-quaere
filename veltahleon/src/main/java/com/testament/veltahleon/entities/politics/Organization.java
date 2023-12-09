package com.testament.veltahleon.entities.politics;

import com.testament.veltahleon.entities.calendar.Year;
import com.testament.veltahleon.entities.landmark.Nation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String organizationalName;

    @Getter
    @Setter
    private Pundit founder;

    @Getter
    @Setter
    private Nation nation;

    @Getter
    @Setter
    private Year foundationYear;

    @Getter
    @Setter
    private Year disbandmentYear;

    public Organization() {}
}
