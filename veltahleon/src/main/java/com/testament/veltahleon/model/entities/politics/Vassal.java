package com.testament.veltahleon.model.entities.politics;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "national_leaders")
public class Vassal extends NationLeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "suzerain_id")
    private NationLeader suzerain;
}
