package com.testament.veltahleon.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String organizationalName;

    @Getter
    @Setter
    private String foundationYear;

    @Getter
    @Setter
    private String disbandmentYear;

    public Organization() {}

    public Organization(Long id, String organizationalName, String foundationYear, String disbandmentYear) {
        this.id = id;
        this.organizationalName = organizationalName;
        this.foundationYear = foundationYear;
        this.disbandmentYear = disbandmentYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(id, that.id) && Objects.equals(organizationalName, that.organizationalName) && Objects.equals(foundationYear, that.foundationYear) && Objects.equals(disbandmentYear, that.disbandmentYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organizationalName, foundationYear, disbandmentYear);
    }

    @Override
    public String toString() {
        return "Organization: {\n" +
                "\t\tid: " + id +
                ",\n\t\torganizationalName: " + organizationalName +
                ",\n\t\tfoundationYear: " + foundationYear +
                ",\n\t\tdisbandmentYear: " + disbandmentYear + '\n' +
                '}';
    }
}
