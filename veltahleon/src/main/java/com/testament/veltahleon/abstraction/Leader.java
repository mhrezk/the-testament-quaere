package com.testament.veltahleon.abstraction;

import com.testament.veltahleon.model.entities.politics.military.Battle;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Leader extends Human {

    @OneToMany
    @JoinColumn(name = "leader_id")
    private Set<Battle> battles;
}
