package com.testament.veltahleon.model.entities.history.library;

import com.testament.veltahleon.enumerations.Gender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authors")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unique_ID")
    private String uniqueValue;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "pen_name")
    private String penName;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "birth_year_abbreviation")
    private String birthYearAbbreviation;

    @Column(name = "death_year")
    private Integer deathYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

//    @OneToMany(mappedBy = "author", cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    private List<Book> books;

    @Column(name = "biography", columnDefinition = "longtext")
    private String biography;

    @Column(name = "image_URL")
    private String imageURL;
}
