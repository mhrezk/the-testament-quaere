package com.testament.veltahleon.entities.divination;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "tarots")
public class Tarot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private StringBuilder description;

    public Tarot() {
    }

    public Tarot(Long id, String name, StringBuilder description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarot tarot = (Tarot) o;
        return Objects.equals(id, tarot.id) && Objects.equals(name, tarot.name) && Objects.equals(description, tarot.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "Tarot: {\n" +
                "\t\tid: " + id +
                ",\n\t\tname: " + name +
                ",\n\t\tdescription: " + description + '\n' +
                '}';
    }
}
