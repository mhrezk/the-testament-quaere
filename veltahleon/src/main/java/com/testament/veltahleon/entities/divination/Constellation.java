package com.testament.veltahleon.entities.divination;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "constellations")
public class Constellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private StringBuilder description;

    public Constellation() {
    }

    public Constellation(Long id, String name, StringBuilder description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constellation that = (Constellation) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "Constellation: {\n" +
                "\t\tid: " + id +
                ",\n\t\tname: " + name +
                ",\n\t\tdescription: " + description + '\n' +
                '}';
    }
}
