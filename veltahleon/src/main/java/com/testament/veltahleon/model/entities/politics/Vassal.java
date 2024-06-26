//package com.testament.veltahleon.model.entities.politics;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
//import lombok.*;
//
//@EqualsAndHashCode(callSuper = true)
//@Entity
//@Getter
//@Setter
////@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "vassals")
//@ToString
//public class Vassal extends NationLeader {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "suzerain_id")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private NationLeader suzerain;
//}
