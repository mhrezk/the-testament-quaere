//package com.testament.veltahleon.model.entities.society.members;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import lombok.*;
//
//@Entity
//@Table(name = "mothers")
//@Builder
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@EqualsAndHashCode
//public class Mother {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotNull(message = "Name cannot be null!")
//    @NotBlank(message = "Name cannot be blank!")
//    @NotEmpty(message = "Name cannot be empty!")
//    @Column(name = "first_name")
//    private String firstName;
//
//    @NotNull(message = "Name cannot be null!")
//    @NotBlank(message = "Name cannot be blank!")
//    @NotEmpty(message = "Name cannot be empty!")
//    @Column(name = "second_name")
//    private String secondName;
//}
