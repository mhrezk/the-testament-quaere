package com.testament.veltahleon.model.entities.calendar;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "epochs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Epoch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year_name", unique = true) //e.g: Year of Six Emperors
    private String yearName;

    @NotNull(message = "Calendar abbreviation cannot be null!")
    @NotBlank(message = "Calendar abbreviation cannot be blank!")
    @NotEmpty(message = "Calendar abbreviation cannot be empty!")
    @Column(name = "year_abbreviation") //e.g: ADE
    private String abbreviation;

    @NotNull(message = "Year number cannot be null!")
    @NotBlank(message = "Year number cannot be blank!")
    @NotEmpty(message = "Year number cannot be empty!")
    @Column(name = "year_number") //e.g: 3000
    private int yearNumber;

    @Column(name = "description", columnDefinition = "text")
    private String description;
}
