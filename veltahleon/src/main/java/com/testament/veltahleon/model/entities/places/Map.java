package com.testament.veltahleon.model.entities.places;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
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

    @Column(name = "world_name")
    private String worldName;

//    @ElementCollection
//    @CollectionTable(name = "map_images",
//            joinColumns = @JoinColumn(name = "map_id"))
//    @MapKeyColumn(name = "images")
//    @Column(name = "image_names_image_URLs")
//    private HashMap<String, String> imagesNameAndURLs = new HashMap<>();

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "image_names", joinColumns = @JoinColumn(name = "map_id"))
    @Column(name = "image_names")
    private List<String> imageNames;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "image_URLs", joinColumns = @JoinColumn(name = "map_id"))
    @Column(name = "image_URLs")
    private List<String> imageURLs;
}
