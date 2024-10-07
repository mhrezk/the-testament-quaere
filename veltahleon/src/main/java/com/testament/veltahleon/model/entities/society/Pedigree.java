package com.testament.veltahleon.model.entities.society;

import com.testament.veltahleon.enumerations.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedigrees")
@EqualsAndHashCode
@ToString
public class Pedigree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lineage_name")
    private String lineageName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @ElementCollection(targetClass = Long.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "spouses", joinColumns = @JoinColumn(name = "pedigree_id"))
    private List<Long> pids;

    @Column(name = "mother_id")
    private Long mid;

    @Column(name = "father_id")
    private Long fid;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "image_URL")
    private String imageURL;

//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "wife_id")
//    private List<Mother> wives;
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "husband_id")
//    private List<Father> husbands;
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "child_id")
//    private List<Child> children;

//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "pedigree_id")
//    private List<Long> pids;
}
