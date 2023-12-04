package com.testament.veltahleon.entities.politics;

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
    private Pundit founder;

    @Getter
    @Setter
    private String foundationYear;

    @Getter
    @Setter
    private String disbandmentYear;

    public Organization() {}

    public Organization(Long id, String organizationalName, Pundit founder, String foundationYear, String disbandmentYear) {
        this.id = id;
        this.organizationalName = organizationalName;
        this.founder = founder;
        this.foundationYear = foundationYear;
        this.disbandmentYear = disbandmentYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(id, that.id) && Objects.equals(organizationalName, that.organizationalName) && Objects.equals(founder, that.founder) && Objects.equals(foundationYear, that.foundationYear) && Objects.equals(disbandmentYear, that.disbandmentYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organizationalName, founder, foundationYear, disbandmentYear);
    }

    @Override
    public String toString() {
        return "Organization: {\n" +
                "\t\tid: " + id +
                ",\n\t\torganizationalName: " + organizationalName +
                ",\n\t\tfounder: " + founder +
                ",\n\t\tfoundationYear: " + foundationYear +
                ",\n\t\tdisbandmentYear: " + disbandmentYear + '\n' +
                '}';
    }
}
