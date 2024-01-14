package com.testament.veltahleon.model.entities.history;

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
    private StringBuilder body;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
