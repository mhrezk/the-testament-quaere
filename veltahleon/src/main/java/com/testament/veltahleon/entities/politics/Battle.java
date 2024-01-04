package com.testament.veltahleon.entities.politics;

import java.util.List;

import com.testament.veltahleon.entities.calendar.Year;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "battles")
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String battleName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinTable(name = "national_leaders_battles", joinColumns = @JoinColumn(name = "national_leader_id"),
            inverseJoinColumns = @JoinColumn(name = "battle_id"))
    @Getter
    @Setter
    private List<NationLeader> nationalLeaders;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinTable(name = "militaristic_leaders_battles", joinColumns = @JoinColumn(name = "militaristic_leader_id"),
            inverseJoinColumns = @JoinColumn(name = "battle_id"))
    @Getter
    @Setter
    private List<MilitaryLeader> militaristicLeaders;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinTable(name = "armies_battles", joinColumns = @JoinColumn(name = "army_id"),
            inverseJoinColumns = @JoinColumn(name = "battle_id"))
    @Getter
    @Setter
    private List<Army> armies;

    @Getter
    @Setter
    private Year battleYear;

    @Getter
    @Setter
    private StringBuilder battleDescription;

    public Battle() {
    }
}
