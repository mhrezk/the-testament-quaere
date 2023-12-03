package com.testament.veltahleon.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "nations")
public class Nation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String nationName;

    @Getter
    @Setter
    private String nationCapital;

    @Getter
    @Setter
    private String nationContinent;

    public Nation() {

    }

    public Nation(Long id, String nationName, String nationCapital, String nationContinent) {
        this.id = id;
        this.nationName = nationName;
        this.nationCapital = nationCapital;
        this.nationContinent = nationContinent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nation nation = (Nation) o;
        return Objects.equals(id, nation.id) && Objects.equals(nationName, nation.nationName) && Objects.equals(nationCapital, nation.nationCapital) && Objects.equals(nationContinent, nation.nationContinent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nationName, nationCapital, nationContinent);
    }

    @Override
    public String toString() {
        return "Nation: {\n" +
                "\t\tid: " + id +
                ",\n\t\tnationName: " + nationName +
                ",\n\t\tnationCapital: " + nationCapital +
                ",\n\t\tnationContinent: " + nationContinent + '\n' +
                '}';
    }
}
