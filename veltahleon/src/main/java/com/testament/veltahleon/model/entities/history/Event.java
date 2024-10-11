package com.testament.veltahleon.model.entities.history;

import com.testament.veltahleon.model.entities.calendar.Year;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "events")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "incident", columnDefinition = "text")
    private String incident;

    @Column(name = "event_day")
    private Integer eventDay;

    @Column(name = "event_month")
    private Integer eventMonth;

    @Column(name = "event_year")
    private Integer eventYear;

    @Column(name = "year_abbreviation")
    private String yearAbbreviation;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "beginning_year_id")
//    private Year beginningYear;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "ending_year_id")
//    private Year endingYear;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "timeline_id")
    private Timeline timeline;
}
