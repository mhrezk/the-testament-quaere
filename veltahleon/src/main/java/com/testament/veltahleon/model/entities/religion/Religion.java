package com.testament.veltahleon.model.entities.religion;

import com.testament.veltahleon.model.entities.religion.mythology.Pantheon;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import jakarta.persistence.*;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "pantheon_id")
    private Pantheon pantheon;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;
}
