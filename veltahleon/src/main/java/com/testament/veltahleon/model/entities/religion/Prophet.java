package com.testament.veltahleon.model.entities.religion;

import com.testament.veltahleon.model.entities.places.Nation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prophets")
public class Prophet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "nation_id")
    private Nation nation;

    @Column(name = "description", columnDefinition = "longtext")
    private StringBuilder description;
}
