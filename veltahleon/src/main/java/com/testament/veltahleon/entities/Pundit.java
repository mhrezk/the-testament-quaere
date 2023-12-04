package com.testament.veltahleon.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "pundits")

public class Pundit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Race race;

    @Getter
    @Setter
    private Organization organization;

    public Pundit() {}

    public Pundit(Long id, String name, Race race, Organization organization) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pundit pundit = (Pundit) o;
        return Objects.equals(id, pundit.id) && Objects.equals(name, pundit.name) && Objects.equals(race, pundit.race) && Objects.equals(organization, pundit.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, race, organization);
    }

    @Override
    public String toString() {
        return "Pundit: {\n" +
                "\t\tid: " + id +
                ",\n\t\tname: " + name +
                ",\n\t\trace: " + race +
                ",\n\t\torganization: " + organization + '\n' +
                '}';
    }
}
