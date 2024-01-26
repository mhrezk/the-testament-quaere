package com.testament.veltahleon.model.entities.religion.mythology;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<Deity> deities;
}
