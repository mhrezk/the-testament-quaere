package com.testament.veltahleon.model.entities.dogma.mythology;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testament.veltahleon.model.entities.dogma.Religion;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deities")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Deity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REMOVE
    })
    @JoinColumn(name = "religion_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Religion religion;

    @Column(columnDefinition = "longtext")
    private String description;

    @Column(name = "image_URL")
    private String imageURL;

    @Column(name = "power_domain")
    private String powerDomain;
}
