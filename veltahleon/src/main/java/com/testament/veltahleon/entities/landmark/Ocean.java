package com.testament.veltahleon.entities.landmark;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "oceans")
public class Ocean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private StringBuilder description;

    public Ocean() {
    }

    public Ocean(Long id, String name, StringBuilder description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ocean ocean = (Ocean) o;
        return Objects.equals(id, ocean.id) && Objects.equals(name, ocean.name) && Objects.equals(description, ocean.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "Ocean: {\n" +
                "\t\tid: " + id +
                ",\n\t\tname: " + name +
                ",\n\t\tdescription: " + description + '\n' +
                '}';
    }
}
