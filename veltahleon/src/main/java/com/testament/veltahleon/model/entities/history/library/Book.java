package com.testament.veltahleon.model.entities.history.library;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_title")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "author_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Author author;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;

    @Column(name = "release_date")
    private Integer releaseDate;

    @Column(name = "release_year_abbreviation")
    private String releaseYearAbbreviation;

    @Column(name = "cover_URL")
    private String coverURL;

//    @OneToMany(mappedBy = "book", cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    private List<Chapter> chapters;
}
