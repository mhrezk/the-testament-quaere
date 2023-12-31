package com.testament.veltahleon.entities.society;

import com.testament.veltahleon.abstraction.Human;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "people")
public class Person extends Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Person() {}
}
