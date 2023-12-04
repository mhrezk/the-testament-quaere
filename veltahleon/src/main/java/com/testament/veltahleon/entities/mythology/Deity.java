package com.testament.veltahleon.entities.mythology;

import com.testament.veltahleon.entities.landmark.Nation;
import com.testament.veltahleon.entities.politics.Organization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "deities")
public class Deity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Nation nationalAffiliation;

    @Getter
    @Setter
    private Organization organizationalAffiliation;

    @Getter
    @Setter
    private StringBuilder description;

    public Deity() {
    }

    public Deity(Long id, String name, Nation nationalAffiliation, Organization organizationalAffiliation, StringBuilder description) {
        this.id = id;
        this.name = name;
        this.nationalAffiliation = nationalAffiliation;
        this.organizationalAffiliation = organizationalAffiliation;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deity deity = (Deity) o;
        return Objects.equals(id, deity.id) && Objects.equals(name, deity.name) && Objects.equals(nationalAffiliation, deity.nationalAffiliation) && Objects.equals(organizationalAffiliation, deity.organizationalAffiliation) && Objects.equals(description, deity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nationalAffiliation, organizationalAffiliation, description);
    }

    @Override
    public String toString() {
        return "Deity: {\n" +
                "\n\t\tid: " + id +
                ",\n\t\tname: " + name +
                ",\n\t\tnationalAffiliation: " + nationalAffiliation +
                ",\n\t\torganizationalAffiliation: " + organizationalAffiliation +
                ",\n\t\tdescription: " + description + '\n' +
                '}';
    }
}
