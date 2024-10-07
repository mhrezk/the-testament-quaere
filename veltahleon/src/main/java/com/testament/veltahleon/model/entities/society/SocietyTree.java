package com.testament.veltahleon.model.entities.society;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "families")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SocietyTree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tree_name")
    private String treeName;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "family_tree_id")
    private List<Pedigree> pedigrees;

//    @OneToOne(mappedBy = "family", cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    private Person person;

//    @OneToOne(cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "person_id")
//    private Human person;

//    @Column(name = "person_name")
//    private String personName;
//
//    @NotNull(message = "Father name cannot be null!")
//    @NotBlank(message = "Father name cannot be blank!")
//    @NotEmpty(message = "Father name cannot be empty!")
//    @Column(name = "father_name")
//    private String fatherName;
//
//    @NotNull(message = "Mother name cannot be null!")
//    @NotBlank(message = "Mother name cannot be blank!")
//    @NotEmpty(message = "Mother name cannot be empty!")
//    @Column(name = "mother_name")
//    private String motherName;
//
//    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
//    @CollectionTable(name = "siblings", joinColumns = @JoinColumn(name = "family_id"))
//    @Column(name = "siblings", nullable = true)
//    private List<String> siblings;
//
//    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
//    @CollectionTable(name = "spouses", joinColumns = @JoinColumn(name = "family_id"))
//    @Column(name = "spouses", nullable = true)
//    private List<String> spouses;
//
//    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
//    @CollectionTable(name = "children", joinColumns = @JoinColumn(name = "family_id"))
//    private List<String> children;
//
//    @NotNull(message = "Family name cannot be null!")
//    @NotBlank(message = "Family name cannot be blank!")
//    @NotEmpty(message = "Family name cannot be empty!")
//    @Column(name = "familial_name")
//    private String familyName;
//
//    @Enumerated(EnumType.STRING)
//    private Lineage lineage;

    //Y or N
//    @Convert(converter = org.hibernate.type.YesNoConverter.class)
//    @Column(name = "is_adopted")
//    private Boolean isAdopted;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "is_bastard")
//    private ClosedAnswer isBastard;
//
//    @Column(name = "coat_of_arms_url")
//    private String urlCoatOfArms;

}
