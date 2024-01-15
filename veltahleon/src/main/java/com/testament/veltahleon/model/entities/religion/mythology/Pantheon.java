package com.testament.veltahleon.model.entities.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Deity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "pantheons")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pantheon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "pantheon_id")
    private Set<Deity> deities;
}
