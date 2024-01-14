package com.testament.veltahleon.model.entities.calendar;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "epochs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Epoch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "epoch_name") //e.g: Age of Stillness
    private String name;

    @Column(name = "epoch_year_name") //e.g: Year of Six Emperors
    private String epochTime;

    @Column(name = "epoch_year_abbreviation") //e.g: ADE
    private String epochAbbreviation;

    @Column(name = "epoch_year_number") //e.g: 3000
    private Integer yearNumber;

    @Column(name = "description", columnDefinition = "text")
    private StringBuilder description;
}
