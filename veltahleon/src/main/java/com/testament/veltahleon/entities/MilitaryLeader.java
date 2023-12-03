package com.testament.veltahleon.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private String nationName;

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

    public MilitaryLeader(Long id, String leaderName, String nationName, String birthYear, String beginningYear, String endYear) {
        this.id = id;
        this.leaderName = leaderName;
        this.nationName = nationName;
        this.birthYear = birthYear;
        this.beginningYear = beginningYear;
        this.endYear = endYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MilitaryLeader that = (MilitaryLeader) o;
        return Objects.equals(id, that.id) && Objects.equals(leaderName, that.leaderName) && Objects.equals(nationName, that.nationName) && Objects.equals(birthYear, that.birthYear) && Objects.equals(beginningYear, that.beginningYear) && Objects.equals(endYear, that.endYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leaderName, nationName, birthYear, beginningYear, endYear);
    }

    @Override
    public String toString() {
        return "MilitaryLeader: {\n" +
                "\t\tid: " + id +
                ",\n\t\tleaderName: " + leaderName +
                ",\n\t\tnationName: " + nationName +
                ",\n\t\tbirthYear: " + birthYear +
                ",\n\t\tbeginningYear: " + beginningYear +
                ",\n\t\tendYear: " + endYear + '\n' +
                '}';
    }
}
