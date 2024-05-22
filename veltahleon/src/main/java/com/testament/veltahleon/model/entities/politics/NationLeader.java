//package com.testament.veltahleon.model.entities.politics;
//
//import com.testament.veltahleon.abstraction.Leader;
//import com.testament.veltahleon.model.entities.calendar.Year;
//import com.testament.veltahleon.model.entities.places.Nation;
//import jakarta.persistence.*;
//import lombok.*;
//import lombok.experimental.SuperBuilder;
//
//import java.util.List;
//
//@EqualsAndHashCode(callSuper = true)
//@Entity
//@Getter
//@Setter
//@SuperBuilder
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "national_leaders")
//@ToString
//public class NationLeader extends Leader {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @Column(name = "year")
//    private List<Year> yearBeginningAndEnd;
//
//    @OneToMany(mappedBy = "suzerain", cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    private List<Vassal> vassals;
//}
