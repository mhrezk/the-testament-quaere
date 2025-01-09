package com.testament.veltahleon.model.entities.encyclopedia.creatures;

import com.testament.veltahleon.enumerations.Category;
import com.testament.veltahleon.enumerations.Classification;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animals")
@EqualsAndHashCode
@ToString
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null!")
    @NotEmpty(message = "Name cannot be empty!")
    @NotBlank(message = "Name cannot be blank!")
    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "classification")
    private Classification classification;

    @Column(name = "sub_classification")
    private String subClassification;

    @Column(name = "image_URL")
    private String imageURL;
}
