package com.testament.veltahleon.entities.history;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "excerpts")
public class Excerpt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private StringBuilder description;

    public Excerpt() {
    }

    public Excerpt(Long id, StringBuilder description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Excerpt excerpt = (Excerpt) o;
        return Objects.equals(id, excerpt.id) && Objects.equals(description, excerpt.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "Excerpt: {\n" +
                "\t\tid: " + id +
                ",\n\t\tdescription: " + description + '\n' +
                '}';
    }
}
