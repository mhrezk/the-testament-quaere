package com.testament.veltahleon.model.entities.encyclopedia.microorganisms.pathology;

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
@Table(name = "medicines")
@EqualsAndHashCode
@ToString
public class Medicine {

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

    @Column(name = "image_URL")
    private String imageURL;
}
