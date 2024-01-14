package com.testament.veltahleon.model.entities.calendar;

import com.testament.veltahleon.model.entities.history.Language;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "days")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "number_of_day")
    private Integer dayNumber;

    @OneToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(name = "description", columnDefinition = "text")
    private StringBuilder description;
}
