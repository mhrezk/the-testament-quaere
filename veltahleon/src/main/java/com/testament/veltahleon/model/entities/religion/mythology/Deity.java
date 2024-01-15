package com.testament.veltahleon.model.entities.religion.mythology;

import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.politics.Organization;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deities")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Deity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "longtext")
    private StringBuilder description;

    @Column(name = "image_URL")
    private String imageURL;
}
