package com.testament.veltahleon.model.entities.dogma.mythology;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pantheons")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Pantheon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "pantheon_id")
    private List<Deity> deities;

    //Convenience Method
    public void addDeity(Deity deity) {
        if(deities == null) {
            deities = new ArrayList<>();
        }
        deities.add(deity);
    }
}
