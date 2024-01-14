package com.testament.veltahleon.model.entities.history;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "races")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String racialName;

    @Column(name = "description", columnDefinition = "text")
    private StringBuilder racialDescription;
}
