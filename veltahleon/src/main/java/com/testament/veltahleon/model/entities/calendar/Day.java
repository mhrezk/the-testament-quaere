package com.testament.veltahleon.model.entities.calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testament.veltahleon.model.entities.history.Language;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.Objects;

@Entity
@Table(name = "days")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_of_day")
    private Integer dayNumber;

    //@PrimaryKeyJoinColumn
//    @Cascade({CascadeType.REFRESH,
////            CascadeType.DETACH,
////            CascadeType.MERGE,
////            CascadeType.PERSIST})
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Language language;

    @Column(name = "description", columnDefinition = "text")
    private String description;
}
