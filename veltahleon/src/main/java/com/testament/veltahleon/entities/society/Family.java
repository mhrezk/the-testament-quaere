package com.testament.veltahleon.entities.society;

import com.testament.veltahleon.entities.politics.Rank;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "families")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private Person fatherName;

    @Getter
    @Setter
    private Person motherName;

    @Getter
    @Setter
    private List<Person> siblingNames;

    @Getter
    @Setter
    private List<Person> spousalNames;

    @Getter
    @Setter
    private List<Person> childrenNames;

    @Getter
    @Setter
    private Boolean isAdopted;
}
