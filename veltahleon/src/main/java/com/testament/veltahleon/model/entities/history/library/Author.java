package com.testament.veltahleon.model.entities.history.library;

import com.testament.veltahleon.abstraction.Human;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "authors")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Author extends Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "authorName", cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    private List<Book> books;
}
