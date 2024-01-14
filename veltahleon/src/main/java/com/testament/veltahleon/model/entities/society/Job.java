package com.testament.veltahleon.model.entities.society;

import com.testament.veltahleon.model.entities.society.enumeration.JobType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_name")
    private String jobName;

    @Enumerated(EnumType.STRING)
    private JobType jobType;
}
