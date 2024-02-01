package com.testament.veltahleon.model.entities.calendar;

import jakarta.persistence.*;
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

    @Column(name = "year_name") //e.g: Year of Six Emperors
    private String yearName;

    @Column(name = "year_abbreviation") //e.g: ADE
    private String abbreviation;

    @Column(name = "year_number") //e.g: 3000
    private Integer yearNumber;

    @Column(name = "description", columnDefinition = "text")
    private String description;
}
