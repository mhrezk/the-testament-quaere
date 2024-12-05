package com.testament.veltahleon.model.entities.politics;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organizations")
@EqualsAndHashCode
@ToString
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "pundit_id")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private Pundit founder;

    @Column(name = "founder_first_name")
    private String founderFirstName;

    @Column(name = "founder_second_name")
    private String founderSecondName;

    @Column(name = "leader_first_name")
    private String leaderFirstName;

    @Column(name = "leader_second_name")
    private String leaderSecondName;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "person_id")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private Person founder;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "national_id")
//    private Nation nation;

//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @MapKeyColumn(name = "foundation_or_disbandment")
//    @Column(name = "year")
//    private List<Year> yearFoundationAndDisbandment;

    @Column(name = "foundation_day")
    private Integer foundationDay;

    @Column(name = "foundation_month")
    private Integer foundationMonth;

    @Column(name = "foundation_year")
    private Integer foundationYear;

    @Column(name = "foundation_year_abbreviation")
    private String foundationYearAbbreviation;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "foundation_year_id")
//    private Year foundationYear;

    @Column(name = "disbandment_day")
    private Integer disbandmentDay;

    @Column(name = "disbandment_month")
    private Integer disbandmentMonth;

    @Column(name = "disbandment_year")
    private Integer disbandmentYear;

    @Column(name = "disbandment_year_abbreviation")
    private String disbandmentYearAbbreviation;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
//    @JoinColumn(name = "disbandment_year_id")
//    private Year disbandmentYear;

    @Column(name = "organization_size")
    private Integer organizationSize;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;

    @Column(name = "symbol_URL")
    private String symbolURL;
}
