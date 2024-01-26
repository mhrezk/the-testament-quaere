package com.testament.veltahleon.abstraction;

import com.testament.veltahleon.model.entities.politics.military.Battle;
import com.testament.veltahleon.model.entities.society.Title;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Leader extends Human {

    @OneToMany
    @JoinColumn(name = "leader_id")
    private List<Battle> battles;

    @OneToOne
    @JoinColumn(name = "title_id")
    //@Column(name = "epithet") //cannot be used with @OneToOne
    @PrimaryKeyJoinColumn(name = "epithet")
    private Title title;

    @Column(name = "coat_of_arms_url")
    private String urlCoatOfArms;
}
