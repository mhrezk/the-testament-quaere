package com.testament.veltahleon.entities.politics;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

    @Getter
    @Setter
    private NationLeader proponentNationalLeader;

    @Getter
    @Setter
    private MilitaryLeader proponentLeader;

    @Getter
    @Setter
    private int proponentArmySize;

    @Getter
    @Setter
    private NationLeader opponentNationalLeader;

    @Getter
    @Setter
    private MilitaryLeader opponentLeader;

    @Getter
    @Setter
    private int opponentArmySize;

    @Getter
    @Setter
    private String battleYear;

    @Getter
    @Setter
    private StringBuilder battleDescription;

    public Battle() {

    }

    public Battle(Long id, String battleName, NationLeader proponentNationalLeader, MilitaryLeader proponentLeader,
                  int proponentArmySize, NationLeader opponentNationalLeader,
                  MilitaryLeader opponentLeader, int opponentArmySize, String battleYear , StringBuilder battleDescription) {
        this.id = id;
        this.battleName = battleName;
        this.proponentNationalLeader = proponentNationalLeader;
        this.proponentLeader = proponentLeader;
        this.proponentArmySize = proponentArmySize;
        this.opponentNationalLeader = opponentNationalLeader;
        this.opponentLeader = opponentLeader;
        this.opponentArmySize = opponentArmySize;
        this.battleYear = battleYear;
        this.battleDescription = battleDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battle battle = (Battle) o;
        return proponentArmySize == battle.proponentArmySize && opponentArmySize == battle.opponentArmySize && Objects.equals(id, battle.id) && Objects.equals(battleName, battle.battleName) && Objects.equals(proponentNationalLeader, battle.proponentNationalLeader) && Objects.equals(proponentLeader, battle.proponentLeader) && Objects.equals(opponentNationalLeader, battle.opponentNationalLeader) && Objects.equals(opponentLeader, battle.opponentLeader) && Objects.equals(battleDescription, battle.battleDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, battleName, proponentNationalLeader, proponentLeader, proponentArmySize, opponentNationalLeader, opponentLeader, opponentArmySize, battleDescription);
    }

    @Override
    public String toString() {
        return "Battle: {\n" +
                "\t\tid: " + id +
                ",\n\t\tbattleName: " + battleName +
                ",\n\t\tproponentNationalLeader: " + proponentNationalLeader.getLeaderName() +
                ",\n\t\tproponentLeader: " + proponentLeader.getLeaderName() +
                ",\n\t\tproponentArmySize: " + proponentArmySize +
                ",\n\t\topponentNationalLeader: " + opponentNationalLeader.getLeaderName() +
                ",\n\t\topponentLeader: " + opponentLeader.getLeaderName() +
                ",\n\t\topponentArmySize: " + opponentArmySize +
                ",\n\t\tbattleDescription: " + battleDescription + '\n' +
                '}';
    }
}
