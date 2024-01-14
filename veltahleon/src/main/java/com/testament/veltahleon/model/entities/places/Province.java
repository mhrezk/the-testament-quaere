package com.testament.veltahleon.model.entities.places;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "provinces")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String provincialName;

    @OneToOne
    @JoinColumn(name = "province_id")
    private Capital provincialCapital;

    @ManyToOne
    @JoinColumn(name = "nation_id")
    private Nation provincialNation;

    @Column(name = "description", columnDefinition = "text")
    private StringBuilder provincialDescription;
}
