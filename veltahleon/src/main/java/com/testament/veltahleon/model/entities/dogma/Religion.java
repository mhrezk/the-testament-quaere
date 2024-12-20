package com.testament.veltahleon.model.entities.dogma;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testament.veltahleon.model.entities.dogma.mythology.Deity;
import com.testament.veltahleon.model.entities.places.Nation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "religions")
@EqualsAndHashCode
@ToString
public class Religion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null!")
    @NotEmpty(message = "Name cannot be empty!")
    @NotBlank(message = "Name cannot be blank!")
    @Column(name = "name")
    private String name;

//    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "pantheon_id")
//    private Pantheon pantheon;

//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "religion_id")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private List<Deity> deities;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REMOVE})
    @JoinTable(name="religions_nations",
            joinColumns={@JoinColumn(name="religion_id")},
            inverseJoinColumns={@JoinColumn(name="nation_id")})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Nation> nations;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;

    @Column(name = "symbol_URL")
    private String symbolURL;
}
