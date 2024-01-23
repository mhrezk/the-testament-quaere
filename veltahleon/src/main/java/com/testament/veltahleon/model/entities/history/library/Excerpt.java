package com.testament.veltahleon.model.entities.history.library;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "excerpts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Excerpt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "excerpt_body", columnDefinition = "text")
    private String body;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
