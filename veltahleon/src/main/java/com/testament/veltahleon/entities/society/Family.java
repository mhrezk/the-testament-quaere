package com.testament.veltahleon.entities.society;

import com.testament.veltahleon.entities.politics.Rank;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@Table(name = "families")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private StringBuilder fatherName;

    @Getter
    @Setter
    private StringBuilder motherName;

    @Getter
    @Setter
    private List<StringBuilder> siblingNames;

    @Getter
    @Setter
    private List<StringBuilder> spousalNames;

    @Getter
    @Setter
    private List<StringBuilder> childrenNames;

    @Getter
    @Setter
    private Boolean isAdopted;
}
