package com.testament.veltahleon.model.entities.places;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "maps")
@EqualsAndHashCode
@ToString
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "image_URLs", joinColumns = @JoinColumn(name = "map_id"))
    @Column(name = "image_URLs")
    private List<String> imageURLs;
}
