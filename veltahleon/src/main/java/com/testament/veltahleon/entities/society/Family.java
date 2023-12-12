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
    private String fatherName;

    @Getter
    @Setter
    private String motherName;

    @Getter
    @Setter
    private List<String> siblingNames;

    @Getter
    @Setter
    private List<String> spousalNames;

    @Getter
    @Setter
    private List<String> childrenNames;

    @Getter
    @Setter
    private Boolean isAdopted;
}
