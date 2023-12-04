package com.testament.veltahleon.entities.mythology;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "angels")
public class Angel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private StringBuilder description;

    public Angel() {
    }

    public Angel(Long id, String name, StringBuilder description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Angel angel = (Angel) o;
        return Objects.equals(id, angel.id) && Objects.equals(name, angel.name) && Objects.equals(description, angel.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "Angel: {\n" +
                "\t\tid: " + id +
                ",\n\t\tname: " + name +
                ",\n\t\tdescription: " + description + '\n' +
                '}';
    }
}
