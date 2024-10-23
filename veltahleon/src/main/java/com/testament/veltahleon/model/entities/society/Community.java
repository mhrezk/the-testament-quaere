package com.testament.veltahleon.model.entities.society;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(name = "community_size", columnDefinition="Integer default '0'")
    //@ColumnDefault("0")
    private Integer communitySize;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;
}
