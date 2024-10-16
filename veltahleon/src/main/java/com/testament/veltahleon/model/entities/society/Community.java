package com.testament.veltahleon.model.entities.society;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "communities")
@EqualsAndHashCode
@ToString
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "community_name")
    private String name;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;
}
