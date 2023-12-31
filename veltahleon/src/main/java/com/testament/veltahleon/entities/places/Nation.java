package com.testament.veltahleon.entities.places;

import com.testament.veltahleon.entities.politics.NationLeader;
import com.testament.veltahleon.entities.politics.Pundit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "nations")
public class Nation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String nationalName;

    @Getter
    @Setter
    private Location nationalCapital;

    @Getter
    @Setter
    private Continent nationalContinent;

    @Getter
    @Setter
    private List<Province> nationalProvinces;

    @Getter
    @Setter
    private NationType nationalType;

    @Getter
    @Setter
    private List<NationLeader> vassals;

    @Getter
    @Setter
    private List<NationLeader> suzerains;

    @Getter
    @Setter
    private StringBuilder nationalDescription;

    @Getter
    @Setter
    private Boolean doesNationStillExist;

    public Nation() {}
}
