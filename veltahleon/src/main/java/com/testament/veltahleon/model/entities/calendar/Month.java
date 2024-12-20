package com.testament.veltahleon.model.entities.calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.testament.veltahleon.model.entities.history.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "months")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Month {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Month name cannot be null!")
    @NotBlank(message = "Month name cannot be blank!")
    @NotEmpty(message = "Month name cannot be empty!")
    private String name;

    @Min(value = 1, message = "Number cannot be below 1!")
    @Column(name = "number_of_month")
    private int monthNumber;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "language_id", referencedColumnName = "id")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private Language language;

    //private String languageName;

    @Column(name = "number_of_days")
    private Integer numberOfDays;

    @Column(name = "description", columnDefinition = "text")
    private String description;
}
