package com.testament.veltahleon.model.entities.calendar;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "years")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Year {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "year_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "day_id")
    private Day day;

    @OneToOne
    @JoinColumn(name = "month_id")
    private Month month;

    //Year
    @OneToOne
    @JoinColumn(name = "epoch_id")
    private Epoch epoch;
}
