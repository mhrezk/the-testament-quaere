package com.testament.veltahleon.model.entities.history;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "timelines")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Timeline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long id;

    @Column(name = "timeline_name")
    private String name;

    @Column(name = "beginning_year")
    private Integer beginningYear;

    @Column(name = "ending_year")
    private Integer endingYear;

    @Column(name = "beginning_year_abbreviation")
    private String beginningYearAbbreviation;

    @Column(name = "ending_year_abbreviation")
    private String endingYearAbbreviation;

//    @OneToMany(mappedBy = "timeline")
//    private List<Event> events;
}
