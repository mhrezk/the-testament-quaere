package com.testament.veltahleon.model.entities.history;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_title")
    private String bookName;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author authorName;

    @OneToMany(mappedBy = "book")
    private Set<Chapter> chapters;
}
