package com.testament.veltahleon.entities.politics;

import com.testament.veltahleon.entities.history.Race;
import com.testament.veltahleon.entities.landmark.Nation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "militaristic_leaders")
public class MilitaryLeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String leaderName;

    @Getter
    @Setter
    private Race race;

    @Getter
    @Setter
    private Nation nation;

    private List<Battle> battles;

    @Getter
    @Setter
    private String birthYear;

    @Getter
    @Setter
    private String beginningYear;

    @Getter
    @Setter
    private String endYear;

    public MilitaryLeader() {

    }

    public MilitaryLeader(Long id, String leaderName, Race race, Nation nation, List<Battle> battles, String birthYear, String beginningYear, String endYear) {
        this.id = id;
        this.leaderName = leaderName;
        this.race = race;
        this.nation = nation;
        this.battles = battles;
        this.birthYear = birthYear;
        this.beginningYear = beginningYear;
        this.endYear = endYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MilitaryLeader that = (MilitaryLeader) o;
        return Objects.equals(id, that.id) && Objects.equals(leaderName, that.leaderName) && Objects.equals(race, that.race) && Objects.equals(nation, that.nation) && Objects.equals(battles, that.battles) && Objects.equals(birthYear, that.birthYear) && Objects.equals(beginningYear, that.beginningYear) && Objects.equals(endYear, that.endYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leaderName, race, nation, battles, birthYear, beginningYear, endYear);
    }

    @Override
    public String toString() {
        return "MilitaryLeader: {\n" +
                "\t\tid: " + id +
                ",\n\t\tleaderName: " + leaderName +
                ",\n\t\trace: " + race +
                ",\n\t\tnation: " + nation.getNationalName() +
                ",\n\t\tbattles: " + battles +
                ",\n\t\tbirthYear: " + birthYear +
                ",\n\t\tbeginningYear: " + beginningYear +
                ",\n\t\tendYear: " + endYear + '\n' +
                '}';
    }
}
