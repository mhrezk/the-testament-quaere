package com.testament.veltahleon.entities.society;

import com.testament.veltahleon.abstraction.Human;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import jakarta.persistence.*;

import java.util.List;

@Getter
@Entity
@Table(name = "people")
public class Person extends Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Person() {}
}
