package com.testament.veltahleon.model.entities.politics;

import com.testament.veltahleon.abstraction.Leader;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "army_leaders")
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArmyLeader extends Leader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
