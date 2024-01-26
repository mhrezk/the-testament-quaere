package com.testament.veltahleon.model.entities.calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testament.veltahleon.model.entities.history.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.Objects;

@Entity
@Table(name = "days")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Day name cannot be null!")
    @NotBlank(message = "Day name cannot be blank!")
    @NotEmpty(message = "Day name cannot be empty!")
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //to avoid issues with lazy loading related to ManyToOne relationships, as it would require eager loading instead
    private Language language;

    @Column(name = "description", columnDefinition = "text")
    private String description;

//    @Override
//    public String toString() {
//        return "Day{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", dayNumber=" + dayNumber +
//                ", language=" + language +
//                ", description='" + description + '\'' +
//                '}';
//    }
}
