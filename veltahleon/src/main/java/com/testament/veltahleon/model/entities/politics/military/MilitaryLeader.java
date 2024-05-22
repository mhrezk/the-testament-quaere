//package com.testament.veltahleon.model.entities.politics.military;
//
//import com.testament.veltahleon.abstraction.Leader;
//import com.testament.veltahleon.model.entities.politics.Rank;
//import jakarta.persistence.*;
//import lombok.*;
//
//@EqualsAndHashCode(callSuper = true)
//@Entity
//@Table(name = "militaristic_leaders")
//@Builder
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//public class MilitaryLeader extends Leader {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "rank_id")
//    private Rank rank;
//
//    @OneToOne(mappedBy = "leader", cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    private Battalion battalion;
//}
