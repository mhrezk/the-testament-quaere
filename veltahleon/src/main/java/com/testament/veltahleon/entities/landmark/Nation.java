package com.testament.veltahleon.entities.landmark;

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
    private String nationalName;

    @Getter
    @Setter
    private String nationalCapital;

    @Getter
    @Setter
    private String nationalContinent;

    public Nation() {

    }

    public Nation(Long id, String nationalName, String nationalCapital, String nationalContinent) {
        this.id = id;
        this.nationalName = nationalName;
        this.nationalCapital = nationalCapital;
        this.nationalContinent = nationalContinent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nation nation = (Nation) o;
        return Objects.equals(id, nation.id) && Objects.equals(nationalName, nation.nationalName) && Objects.equals(nationalCapital, nation.nationalCapital) && Objects.equals(nationalContinent, nation.nationalContinent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nationalName, nationalCapital, nationalContinent);
    }

    @Override
    public String toString() {
        return "Nation: {\n" +
                "\t\tid: " + id +
                ",\n\t\tnationName: " + nationalName +
                ",\n\t\tnationCapital: " + nationalCapital +
                ",\n\t\tnationContinent: " + nationalContinent + '\n' +
                '}';
    }
}
