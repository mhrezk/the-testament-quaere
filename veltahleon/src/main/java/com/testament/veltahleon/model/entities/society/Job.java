package com.testament.veltahleon.model.entities.society;

import com.testament.veltahleon.enumerations.JobType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jobs")
@EqualsAndHashCode
@ToString
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_name")
    private String name;

    @Column(name = "job_description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type")
    private JobType jobType;
}
