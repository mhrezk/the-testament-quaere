//package com.testament.veltahleon.model.entities.society;
//
//import com.testament.veltahleon.enumerations.Gender;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//
//@Entity
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "pedigrees")
//@EqualsAndHashCode
//@ToString
//public class Pedigree {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "pedigree_line_name")
//    private String pedigreesLineName;
//
//    @Column(name = "mother_id")
//    private Long mid;
//
//    @Column(name = "father_id")
//    private Long fid;
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "pedigree_id")
//    private List<Long> pids;
//
//    @Enumerated(EnumType.STRING)
//    private Gender gender;
//
//    @Column(name = "family_member_image")
//    private String imageURL;
//}
