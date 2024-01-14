package com.testament.veltahleon.model.entities.politics.military;

import com.testament.veltahleon.abstraction.Leader;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "squad_leaders")
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SquadLeader extends Leader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
