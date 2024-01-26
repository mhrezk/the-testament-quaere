package com.testament.veltahleon.model.entities.politics.military;

import java.util.List;

import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.model.entities.politics.NationLeader;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "battles")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "battle_name")
    private String battleName;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinTable(name = "nation_leaders_battles", joinColumns = @JoinColumn(name = "nation_leader_id"),
//            inverseJoinColumns = @JoinColumn(name = "battle_id"))
////    @ElementCollection(fetch = FetchType.LAZY)
////    @CollectionTable(name = "proponent_opponent_nation_leaders_battles", joinColumns = @JoinColumn(name = "battle_id"))
////    @MapKeyColumn(name = "proponent_or_opponent_nation_leaders")
//    @MapKeyColumn(name = "nation_leaders_proponent_and_opponent")
//    @Column(name = "nation_leaders")
//    private Map<String, NationLeader> nationalLeaders;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinTable(name = "nation_leaders_battles", joinColumns = @JoinColumn(name = "nation_leader_id"),
            inverseJoinColumns = @JoinColumn(name = "battle_id"))
    private List<NationLeader> nationalLeaders;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinTable(name = "army_leaders_battles", joinColumns = @JoinColumn(name = "army_leader_id"),
//            inverseJoinColumns = @JoinColumn(name = "battle_id"))
////    @ElementCollection(fetch = FetchType.LAZY)
////    @CollectionTable(name = "proponent_opponent_military_leaders_battles", joinColumns = @JoinColumn(name = "battle_id"))
////    @MapKeyColumn(name = "proponent_or_opponent_military_leaders")
//    @MapKeyColumn(name = "army_leaders_proponent_and_opponent")
//    @Column(name = "army_leaders")
//    private Map<String, ArmyLeader> armyLeaders;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinTable(name = "armies_battles", joinColumns = @JoinColumn(name = "army_id"),
//            inverseJoinColumns = @JoinColumn(name = "battle_id"))
////    @ElementCollection(fetch = FetchType.LAZY)
////    @CollectionTable(name = "proponent_opponent_armies_battles", joinColumns = @JoinColumn(name = "battle_id"))
////    @MapKeyColumn(name = "proponent_or_opponent_armies")
//    @MapKeyColumn(name = "armies_proponent_and_opponent")
//    @Column(name = "armies")
//    private Map<String, Army> armies;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinTable(name = "armies_battles", joinColumns = @JoinColumn(name = "army_id"),
            inverseJoinColumns = @JoinColumn(name = "battle_id"))
    private List<Army> armies;

    @OneToOne
    @JoinColumn(name = "year_id")
    private Year battleYear;

    @Column(name = "description", columnDefinition = "longtext")
    private String battleDescription;
}
