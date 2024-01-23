package com.testament.veltahleon.model.entities.religion;

import com.testament.veltahleon.model.entities.religion.mythology.Pantheon;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "religions")
public class Religion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "pantheon_id")
    private Pantheon pantheon;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;
}
