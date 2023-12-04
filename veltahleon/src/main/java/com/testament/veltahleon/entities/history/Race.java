package com.testament.veltahleon.entities.history;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String racialName;

    public Race() {}

    public Race(Long id, String racialName) {
        this.id = id;
        this.racialName = racialName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return Objects.equals(id, race.id) && Objects.equals(racialName, race.racialName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, racialName);
    }

    @Override
    public String toString() {
        return "Race: {\n" +
                "\t\tid: " + id +
                ",\n\t\tracialName: " + racialName + '\n' +
                '}';
    }
}
