package com.testament.veltahleon.model.entities.society;

import com.testament.veltahleon.abstraction.Human;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.religion.Religion;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "people")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person extends Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany
////    @ElementCollection(fetch = FetchType.LAZY)
////    @CollectionTable(name = "siblings_people", joinColumns = @JoinColumn(name = "people_id"))
////    @OrderColumn(name = "siblings_index")
//    @Column(name = "siblings")
//    private List<String> siblings;

//    @OneToMany
////    @ElementCollection(fetch = FetchType.LAZY)
////    @CollectionTable(name = "children_people", joinColumns = @JoinColumn(name = "people_id"))
////    @OrderColumn(name = "children_index")
//    @Column(name = "children")
//    private List<String> children;

//    @OneToMany
////    @ElementCollection(fetch = FetchType.LAZY)
////    @CollectionTable(name = "spouses_people", joinColumns = @JoinColumn(name = "people_id"))
////    @OrderColumn(name = "spouses_index")
//    @Column(name = "spouses")
//    private List<String> spouses;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "job_id")
    private Job job;
}
