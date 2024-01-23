package com.testament.veltahleon.model.entities.calendar;

import com.testament.veltahleon.model.entities.history.Language;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "months")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Month {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "number_of_month")
    private Integer monthNumber;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;

    @Column(name = "number_of_days")
    private Integer numberOfDays;

    @Column(name = "description", columnDefinition = "text")
    private String description;
}
