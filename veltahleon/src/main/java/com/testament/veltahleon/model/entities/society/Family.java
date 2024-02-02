package com.testament.veltahleon.model.entities.society;

import com.testament.veltahleon.model.entities.society.enumeration.Lineage;
import com.testament.veltahleon.model.enumeration.ClosedAnswer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "families")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "family", cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    private Person person;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;

    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "siblings", joinColumns = @JoinColumn(name = "family_id"))
    @Column(name = "siblings", nullable = true)
    private List<String> siblings;

    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "spouses", joinColumns = @JoinColumn(name = "family_id"))
    @Column(name = "spouses", nullable = true)
    private List<String> spouses;

    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "children", joinColumns = @JoinColumn(name = "family_id"))
    private List<String> children;

    @Column(name = "familial_name")
    private String familyName;

    @Enumerated(EnumType.STRING)
    private Lineage lineage;

    //Y or N
//    @Convert(converter = org.hibernate.type.YesNoConverter.class)
//    @Column(name = "is_adopted")
//    private Boolean isAdopted;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_bastard")
    private ClosedAnswer isBastard;

    @Column(name = "coat_of_arms_url")
    private String urlCoatOfArms;

}
