package com.testament.veltahleon.entities.history;

import com.testament.veltahleon.entities.landmark.Nation;
import com.testament.veltahleon.entities.politics.Organization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "languages")
public class Language {

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

    public Language() {
    }

    public Language(Long id, String name, Nation nationalAffiliation, Organization organizationalAffiliation) {
        this.id = id;
        this.name = name;
        this.nationalAffiliation = nationalAffiliation;
        this.organizationalAffiliation = organizationalAffiliation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(id, language.id) && Objects.equals(name, language.name) && Objects.equals(nationalAffiliation, language.nationalAffiliation) && Objects.equals(organizationalAffiliation, language.organizationalAffiliation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nationalAffiliation, organizationalAffiliation);
    }

    @Override
    public String toString() {
        return "Language: {\n" +
                "\t\tid: " + id +
                ",\n\t\tname: " + name +
                ",\n\t\tnationalAffiliation: " + nationalAffiliation +
                ",\n\t\torganizationalAffiliation: " + organizationalAffiliation + '\n' +
                '}';
    }
}
