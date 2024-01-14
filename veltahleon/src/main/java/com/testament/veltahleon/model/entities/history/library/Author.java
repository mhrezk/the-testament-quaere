package com.testament.veltahleon.model.entities.history.library;

import com.testament.veltahleon.abstraction.Human;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "authors")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author extends Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "authorName")
    private Set<Book> books;
}
