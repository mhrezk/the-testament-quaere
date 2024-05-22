//package com.testament.veltahleon.model.entities.politics.military;
//
//import com.testament.veltahleon.abstraction.Leader;
//import com.testament.veltahleon.model.entities.politics.Rank;
//import jakarta.persistence.*;
//import lombok.*;
//import lombok.experimental.SuperBuilder;
//
//@EqualsAndHashCode(callSuper = true)
//@Entity
//@Table(name = "army_leaders")
//@SuperBuilder
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//public class ArmyLeader extends Leader {
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
//}
