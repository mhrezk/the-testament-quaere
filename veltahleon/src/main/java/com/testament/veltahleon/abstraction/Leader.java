package com.testament.veltahleon.abstraction;

import com.testament.veltahleon.model.entities.politics.military.Battle;
import com.testament.veltahleon.model.entities.society.Title;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@SuperBuilder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class Leader extends Human {

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "leader_id")
    private List<Battle> battles;
}
